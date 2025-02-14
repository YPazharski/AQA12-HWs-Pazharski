package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import java.time.LocalDate;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static io.qameta.allure.SeverityLevel.*;

/*
Tests are the same as in MainPageTests class. I tried to apply DRY there.
But I am not sure about readability.
So I left both classes.
 */
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
    }

    @BeforeMethod
    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().minimize();
    }

    @BeforeMethod(dependsOnMethods = "initializeDriver")
    public void initializeEditProfileForm() {
        editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
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
    @Story("Сохранить изменения данных профиля")
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
    @Story("Сохранить изменения данных профиля")
    @Issue("https://github.com/YPazharski/AQA12-HWs-Pazharski/blob/d9ceeeea359568fa97d4bd5021801d11b7cf941c/src/test/resources/Lesson18/Lesson_14_Test_cases.xlsx")
    public void maipe9() {
        String newDate = LocalDate.of(1996, 12, 20).format(MainPage.DATE_FORMAT);
        String altNewDate = LocalDate.of(1997, 6, 15).format(MainPage.DATE_FORMAT);
        checkValuesAfterWriteToEditProfileFormField(newDate, altNewDate,
                EditProfileField.DATE_OF_BIRTH,
                epf -> epf.clickClose().clickEditProfileButton().getFieldValue(EditProfileField.DATE_OF_BIRTH),
                false);
    }

}