package by.PazharskiYury.Lesson18;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

//@SuppressWarnings({"UnusedReturnValue", "unused"})
public class MainPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/";
    private final WebDriver driver;

    @FindBy(css = "img[alt='Edit']")
    private WebElement profileEditButton;

    private MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static MainPage open(WebDriver driver, String accountEmail, String accountPassword) throws FailedLoginException{
        LoginPage loginPage = LoginPage.open(driver);
        if (!loginPage.trySignIn(accountEmail, accountPassword)) {
            throw new FailedLoginException(accountEmail, accountPassword);
        }
        return new MainPage(driver);
    }

    public EditProfileForm clickEditProfileButton() {
        profileEditButton.click();
        return new EditProfileForm();
    }

    public class EditProfileForm {

        @FindBy(name = "firstName")
        private  WebElement firstNameField;

        @FindBy(name = "lastName")
        private  WebElement lastNameField;

        @FindBy(name = "email")
        private  WebElement emailField;

        @FindBy(name = "dateOfBirth")
        private  WebElement dateOfBirthField;

        @FindBy(css = "button[type='submit']")
        private  WebElement saveButton;

        @FindBy(css = "button[type='button']")
        private  WebElement cancelButton;

        @FindBy(css = "img[alt='Close']")
        private  WebElement closeButton;

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
            return field.getDomProperty("value");
        }

        public EditProfileForm setFieldValue(EditProfileField field, String newValue) {
            WebElement fieldElement = getWebField(field);
            return setFieldValue(fieldElement, newValue);
        }

        private EditProfileForm setFieldValue(WebElement field, String newValue) {
            clearFieldValue(field);
            field.sendKeys(newValue);
            return this;
        }

        private void clearFieldValue(WebElement field) {
            field.sendKeys(Keys.CONTROL + "a");
            field.sendKeys(Keys.DELETE);
        }

        public MainPage clickSave() throws FailedSaveProfileInfoException {
            if (saveButton.isEnabled()) {
                saveButton.click();
                new Actions(driver).pause(Duration.ofMillis(150)).build().perform(); //without this pause old values are displayed in profile edit fields if the edit form is opened again immediately after clicking Save button.
                return MainPage.this;
            }
            else {
                throw new FailedSaveProfileInfoException(
                        "Save button can't be clicked. Perhaps some of fields have invalid value");
            }
        }

        public MainPage clickCancel() {
            cancelButton.click();
            return MainPage.this;
        }

        public  MainPage clickClose() {
            closeButton.click();
            return MainPage.this;
        }

    }

}