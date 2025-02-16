package by.PazharskiYury.Lesson18;

import by.PazharskiYury.Lesson19.SingleWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import io.qameta.allure.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static io.qameta.allure.SeverityLevel.*;

@SuppressWarnings("NewClassNamingConvention")
@Feature("Окно редактирования профиля")
public class MainPageTestsDryAlternative {

    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";
    private WebDriver driver;
    private MainPage.EditProfileForm editProfileForm;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        initializeDriver();
        generateEnvironmentPropertiesForAllure();
        quitDriver();
    }

    @BeforeMethod
    @Step("Открыть браузер")
    public void initializeDriver() {
        driver = SingleWebDriver.getDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod(dependsOnMethods = "initializeDriver")
    @Step("Открыть главную страницу и нажать на кнопку редактирования профиля")
    public void initializeEditProfileForm() {
        editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
    }

    @AfterMethod
    @Step("Закрыть браузер")
    public void quitDriver() {
        SingleWebDriver.quit();
    }

    private void checkValuesAfterWriteToEditProfileFormField(String newValue, String altNewValue,
                                                             EditProfileField field,
                                                             Function<MainPage.EditProfileForm, String> afterWriteActionToGetActualValue,
                                                             boolean rewriteIsExpected) {
        String pastValue = editProfileForm.getFieldValue(field);
        String valueToWrite = pastValue.equals(newValue) ? altNewValue : newValue;
        editProfileForm.writeFieldValue(field, valueToWrite);
        String actualValue = afterWriteActionToGetActualValue.apply(editProfileForm);
        if (rewriteIsExpected) {
            assertEquals(actualValue, valueToWrite);
        } else {
            assertEquals(actualValue, pastValue);
        }
    }

    @Test
    @Description("Изменение имени на корректное в настройках профиля происходит успешно")
    @Severity(CRITICAL)
    @Story("Сохранить изменения данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe0() {
        checkValuesAfterWriteToEditProfileFormField("Name", "NoName",
                EditProfileField.FIRST_NAME,
                epf -> epf.clickSave().getProfileFirstName(),
                true);
    }

    @Test
    @Description("Изменение фамилии на корректное в настройках профиля происходит успешно")
    @Severity(CRITICAL)
    @Story("Сохранить изменения данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe1() {
        checkValuesAfterWriteToEditProfileFormField("Same", "Nosame",
                EditProfileField.LAST_NAME,
                epf -> epf.clickSave().getProfileLastName(),
                true);

    }

    @Test
    @Description("Изменение даты рождения на корректное путём печати с клавиатуры в настройках профиля происходит успешно")
    @Severity(CRITICAL)
    @Story("Сохранить изменения данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe2() {
        String newDate = LocalDate.of(1996, 12, 20).format(MainPage.DATE_FORMAT);
        String altNewDate = LocalDate.of(1997, 6, 15).format(MainPage.DATE_FORMAT);
        checkValuesAfterWriteToEditProfileFormField(newDate, altNewDate,
                EditProfileField.DATE_OF_BIRTH,
                epf -> epf.clickSave().getProfileDateOfBirthText(),
                true);
    }

    @Test
    @Description("Изменение даты рождения на корректное путём выбора в календаре в настройках профиля происходит успешно")
    @Severity(CRITICAL)
    @Story("Сохранить изменения данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe3() {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        LocalDate pastDate = editProfileForm.getDateOfBirth();
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .selectDateOfBirth(date)
                .clickSave()
                .getProfileDateOfBirth();
        assertEquals(displayingDate, date);
    }

    @Test
    @Description("Кнопка \"Cancel\" в окне изменения данных профиля сбрасывает все изменения в поле \"First Name\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe4() {
        checkValuesAfterWriteToEditProfileFormField("Name", "NoName",
                EditProfileField.FIRST_NAME,
                epf -> epf.clickCancel().clickEditProfileButton().getFieldValue(EditProfileField.FIRST_NAME),
                false);
    }

    @Test
    @Description("Кнопка \"Cancel\" в окне изменения данных профиля сбрасывает все изменения в поле \"Last name\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe5() {
        checkValuesAfterWriteToEditProfileFormField("Name", "NoName",
                EditProfileField.LAST_NAME,
                epf -> epf.clickCancel().clickEditProfileButton().getFieldValue(EditProfileField.LAST_NAME),
                false);
    }

    @Test
    @Description("Кнопка \"Cancel\" в окне изменения данных профиля сбрасывает все изменения в поле \"Date of Birth\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe6() {
        String newDate = LocalDate.of(1996, 12, 20).format(MainPage.DATE_FORMAT);
        String altNewDate = LocalDate.of(1997, 6, 15).format(MainPage.DATE_FORMAT);
        checkValuesAfterWriteToEditProfileFormField(newDate, altNewDate,
                EditProfileField.DATE_OF_BIRTH,
                epf -> epf.clickCancel().clickEditProfileButton().getFieldValue(EditProfileField.DATE_OF_BIRTH),
                false);
    }

    @Test
    @Description("Кнопка \"Закрыть\" в окне изменения данных профиля сбрасывает все изменения в поле \"First Name\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe7() {
        checkValuesAfterWriteToEditProfileFormField("Name", "NoName",
                EditProfileField.FIRST_NAME,
                epf -> epf.clickClose().clickEditProfileButton().getFieldValue(EditProfileField.FIRST_NAME),
                false);
    }

    @Test
    @Description("Кнопка \"Закрыть\" в окне изменения данных профиля сбрасывает все изменения в поле \"Last name\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe8() {
        checkValuesAfterWriteToEditProfileFormField("Name", "NoName",
                EditProfileField.LAST_NAME,
                epf -> epf.clickClose().clickEditProfileButton().getFieldValue(EditProfileField.LAST_NAME),
                false);
    }

    @Test
    @Description("Кнопка \"Закрыть\" в окне изменения данных профиля сбрасывает все изменения в поле \"Date of Birth\"")
    @Severity(CRITICAL)
    @Story("Отменить изменения при редактировании данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe9() {
        String newDate = LocalDate.of(1996, 12, 20).format(MainPage.DATE_FORMAT);
        String altNewDate = LocalDate.of(1997, 6, 15).format(MainPage.DATE_FORMAT);
        checkValuesAfterWriteToEditProfileFormField(newDate, altNewDate,
                EditProfileField.DATE_OF_BIRTH,
                epf -> epf.clickClose().clickEditProfileButton().getFieldValue(EditProfileField.DATE_OF_BIRTH),
                false);
    }

    private void generateEnvironmentPropertiesForAllure() {
        Properties properties = new Properties();
        RemoteWebDriver rDriver = (RemoteWebDriver) driver;
        rDriver.get(LoginPage.URL);
        Capabilities capabilities = rDriver.getCapabilities();
        properties.setProperty("os_platform", System.getProperty("os.name"));
        properties.setProperty("os_version", System.getProperty("os.version"));
        properties.setProperty("java_version", System.getProperty("java.version"));
        properties.setProperty("Chrome_version", capabilities.getBrowserVersion());

        try {
            Files.createDirectories(Paths.get("target/allure-results"));
            try (FileWriter writer = new FileWriter("target/allure-results/environment.properties")) {
                properties.store(writer, "Environment Properties");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}