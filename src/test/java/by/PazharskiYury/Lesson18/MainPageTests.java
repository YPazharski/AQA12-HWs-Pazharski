package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class MainPageTests {

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

    @Test
    public void maipe0() {
        String newName = "Name";
        String altNewName = "Noname";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.FIRST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.FIRST_NAME, name)
                .clickSave()
                .getProfileFirstName();
        assertEquals(displayingName, name);
    }

    @Test
    public void maipe1() {
        String newName = "Same";
        String altNewName = "Nosame";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.LAST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.LAST_NAME, name)
                .clickSave()
                .getProfileLastName();
        assertEquals(displayingName, name);
    }

    @Test
    public void maipe2() {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        LocalDate pastDate = editProfileForm.getDateOfBirth();
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .writeDateOfBirth(date)
                .clickSave()
                .getProfileDateOfBirth();
        assertEquals(displayingDate, date);
    }

    @Test
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
    public void maipe4() {
        String newName = "Name";
        String altNewName = "Noname";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.FIRST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.FIRST_NAME, name)
                .clickCancel()
                .clickEditProfileButton()
                .getFieldValue(EditProfileField.FIRST_NAME);
        assertEquals(displayingName, pastName);
    }

    @Test
    public void maipe5() {
        String newName = "Same";
        String altNewName = "Nosame";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.LAST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.LAST_NAME, name)
                .clickCancel()
                .clickEditProfileButton()
                .getFieldValue(EditProfileField.LAST_NAME);
        assertEquals(displayingName, pastName);
    }

    @Test
    public void maipe6() {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        LocalDate pastDate = editProfileForm.getDateOfBirth();
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .writeDateOfBirth(date)
                .clickCancel()
                .clickEditProfileButton()
                .getDateOfBirth();
        assertEquals(displayingDate, pastDate);
    }

    @Test
    public void maipe7() {
        String newName = "Name";
        String altNewName = "Noname";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.FIRST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.FIRST_NAME, name)
                .clickClose()
                .clickEditProfileButton()
                .getFieldValue(EditProfileField.FIRST_NAME);
        assertEquals(displayingName, pastName);
    }

    @Test
    public void maipe8() {
        String newName = "Same";
        String altNewName = "Nosame";
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.LAST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .writeFieldValue(EditProfileField.LAST_NAME, name)
                .clickClose()
                .clickEditProfileButton()
                .getFieldValue(EditProfileField.LAST_NAME);
        assertEquals(displayingName, pastName);
    }

    @Test
    public void maipe9() {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        LocalDate pastDate = editProfileForm.getDateOfBirth();
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .writeDateOfBirth(date)
                .clickClose()
                .clickEditProfileButton()
                .getDateOfBirth();
        assertEquals(displayingDate, pastDate);
    }

}