package by.PazharskiYury.Lesson18;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SuppressWarnings({"UnusedReturnValue", "unused", "LoggingSimilarMessage"})
public class MainPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class); //If I have more page classes, I will implement logger in WebDriverListener of course.
    private final WebDriver driver;

    @FindBy(css = "img[alt='Edit']")
    private WebElement profileEditButton;

    @FindBy(xpath = "//h1[contains(@class,'text-[56px] leading-[65.63px] font-thin')]")
    private WebElement profileNameText;

    @FindBy(css = "div[class='mt-1.5 text-base leading-6 text-black max-md:mr-1.5']")
    private WebElement profileDateOfBirtText;

    private MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть главную страницу")
    public static MainPage open(WebDriver driver, String accountEmail, String accountPassword) throws FailedLoginException {
        LoginPage loginPage = LoginPage.open(driver);
        if (!loginPage.trySignIn(accountEmail, accountPassword)) {
            throw new FailedLoginException(accountEmail, accountPassword);
        }
        logger.info("Open {}", URL);
        return new MainPage(driver);
    }

    private String getProfileName() {
        var result = profileNameText.getText();
        logger.info("Get text \"{}\" from element {}", result, profileNameText);
        return result;
    }

    public String getProfileDateOfBirthText() {
        var result = profileDateOfBirtText.getText();
        logger.info("Get text \"{}\" from element {}", result, profileDateOfBirtText);
        return result;
    }

    public LocalDate getProfileDateOfBirth() {
        return LocalDate.parse(getProfileDateOfBirthText(), DATE_FORMAT);
    }

    public String getProfileFirstName() {
        return getProfileName().split(" ")[0];
    }

    public String getProfileLastName() {
        return getProfileName().split(" ")[1];
    }

    @Step("Нажать кнопку редактирования профиля")
    public EditProfileForm clickEditProfileButton() {
        profileEditButton.click();
        logger.info("Click on element {}", profileEditButton);
        return new EditProfileForm();
    }

    public class EditProfileForm {

        @FindBy(name = "firstName")
        private WebElement firstNameField;

        @FindBy(name = "lastName")
        private WebElement lastNameField;

        @FindBy(name = "email")
        private WebElement emailField;

        @FindBy(name = "dateOfBirth")
        private WebElement dateOfBirthField;

        @FindBy(css = "button[type='submit']")
        private WebElement saveButton;

        @FindBy(css = "button[type='button']")
        private WebElement cancelButton;

        @FindBy(css = "img[alt='Close']")
        private WebElement closeButton;

        @FindBy(css = "div[class='text-lg font-normal text-[20px] text-[#020303]'] span")
        private WebElement calendarSectionText;

        @FindBy(css = "img[alt='arrow_right']")
        private WebElement calendarRightArrowButton;

        @FindBy(css = "img[alt='arrow_left']")
        private WebElement calendarLeftArrowButton;

        private EditProfileForm() {
            PageFactory.initElements(driver, this);
        }

        private WebElement getWebField(EditProfileField field) {
            return switch (field) {
                case FIRST_NAME -> firstNameField;
                case LAST_NAME -> lastNameField;
                case EMAIL -> emailField;
                case DATE_OF_BIRTH -> dateOfBirthField;
            };
        }

        public String getFieldValue(EditProfileField field) {
            return getFieldValue(getWebField(field));
        }

        private String getFieldValue(WebElement field) {
            var result = field.getDomProperty("value");
            logger.info("Get value \"{}\" from the element {}", result, field);
            return result;
        }

        public EditProfileForm writeFieldValue(EditProfileField field, String newValue) {
            WebElement fieldElement = getWebField(field);
            logger.info("Writing value \"{}\" to element {}", newValue, fieldElement);
            Allure.step(String.format("Попытка записи значения \"%s\" в поле %s формы редактирования профиля...", newValue, field.name()));
            return writeFieldValue(fieldElement, newValue);
        }

        public EditProfileForm writeDateOfBirth(LocalDate newDate) {
            writeFieldValue(EditProfileField.DATE_OF_BIRTH, newDate.format(DATE_FORMAT));
            return this;
        }

        public LocalDate getDateOfBirth() {
            String date = getFieldValue(EditProfileField.DATE_OF_BIRTH);
            return LocalDate.parse(date, MainPage.DATE_FORMAT);
        }

        @Step("Выбор даты рождения в календаре в форме редактирования профиля")
        public EditProfileForm selectDateOfBirth(LocalDate newDate) {
            dateOfBirthField.click();
            logger.info("Click on element {}", dateOfBirthField);
            logger.info("Choosing date {}...", newDate);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            DateTimeFormatter calendarSectionFormat = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
            YearMonth calendarYearMonth = YearMonth.parse(calendarSectionText.getText(), calendarSectionFormat);
            YearMonth newYearMonth = YearMonth.of(newDate.getYear(), newDate.getMonthValue());
            while (calendarYearMonth.isBefore(newYearMonth)) {
                wait.until(ExpectedConditions.elementToBeClickable(calendarRightArrowButton)).click();
                logger.info("Click on element {}", calendarRightArrowButton);
                calendarYearMonth = YearMonth.parse(calendarSectionText.getText(), calendarSectionFormat);
            }
            while (calendarYearMonth.isAfter(newYearMonth)) {
                wait.until(ExpectedConditions.elementToBeClickable(calendarLeftArrowButton)).click();
                logger.info("Click on element {}", calendarLeftArrowButton);
                calendarYearMonth = YearMonth.parse(calendarSectionText.getText(), calendarSectionFormat);
            }
            By dateSelector = By.xpath(String.format("//span[normalize-space()='%d']", newDate.getDayOfMonth()));
            var dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dateSelector));
            dateElement.click();
            logger.info("Click on element {}", dateElement);
            return this;
        }

        private EditProfileForm writeFieldValue(WebElement field, String newValue) {
            clearFieldValue(field);
            field.sendKeys(newValue);
            logger.info("Value \"{}\" write to element {}", newValue, field);
            return this;
        }

        private void clearFieldValue(WebElement field) {
            field.sendKeys(Keys.CONTROL + "a");
            field.sendKeys(Keys.DELETE);
            logger.info("Element {} cleared", field);
        }

        @Step("Нажать кнопку \"Save\" в форме редактирования профиля")
        public MainPage clickSave() throws FailedSaveProfileInfoException {
            if (saveButton.isEnabled()) {
                saveButton.click();
                Duration waitTime = Duration.ofMillis(300);
                new Actions(driver).pause(waitTime).build().perform(); //without this pause old values are displayed in profile info and in edit fields if the edit form is opened again immediately after clicking Save button. Date of birth picked from calendar updates especially slow.
                logger.info("Click element [{}] and wait {} milliseconds", saveButton, waitTime.toMillis());
                return MainPage.this;
            } else {
                throw new FailedSaveProfileInfoException(
                        "Save button can't be clicked. Perhaps some of fields have invalid value");
            }
        }

        @Step("Нажать кнопку \"Cancel\" в форме редактирования профиля")
        public MainPage clickCancel() {
            cancelButton.click();
            Duration waitTime = Duration.ofMillis(300);
            new Actions(driver).pause(waitTime).build().perform();
            logger.info("Click element [{}] and wait {} milliseconds", cancelButton, waitTime.toMillis());
            return MainPage.this;
        }

        @Step("Нажать кнопку \"Close\" (кнопка крестика справа вверху формы) в форме редактирования профиля")
        public MainPage clickClose() {
            closeButton.click();
            Duration waitTime = Duration.ofMillis(300);
            new Actions(driver).pause(waitTime).build().perform();
            logger.info("Click element [{}] and wait {} milliseconds", closeButton, waitTime.toMillis());
            return MainPage.this;
        }

    }

}