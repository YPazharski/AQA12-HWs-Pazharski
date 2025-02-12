package by.PazharskiYury;

import by.PazharskiYury.Lesson18.EditProfileField;
import by.PazharskiYury.Lesson18.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class MainPageTests {

    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

//    @BeforeMethod
//    public void initNewDriver() {
//        driver = new ChromeDriver();
//    }
//
//    @BeforeMethod(dependsOnMethods = "initNewDriver")
//    public void openMainPage() {
//        mainPage = MainPage.open(driver, EMAIL, PASSWORD);
//    }

//    @AfterMethod
//    public void quitDriver() {
//        driver.quit();
//    }

    @Test
    public void maipe0() {
        String newName = "Name";
        String altNewName = "Noname";
        WebDriver driver = new ChromeDriver();
        var editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.FIRST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .setFieldValue(EditProfileField.FIRST_NAME, name)
                .clickSave()
                .getProfileFirstName();
        driver.quit();
        assertEquals(displayingName, name);
    }

    @Test
    public void maipe1() {
        String newName = "Same";
        String altNewName = "Nosame";
        WebDriver driver = new ChromeDriver();
        var editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
        String pastName = editProfileForm
                .getFieldValue(EditProfileField.LAST_NAME);
        String name = pastName.equals(newName) ? altNewName : newName;
        String displayingName = editProfileForm
                .setFieldValue(EditProfileField.LAST_NAME, name)
                .clickSave()
                .getProfileLastName();
        driver.quit();
        assertEquals(displayingName, name);
    }

    @Test
    public void maipe2() {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        WebDriver driver = new ChromeDriver();
        var editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
        String pastDateString = editProfileForm
                .getFieldValue(EditProfileField.DATE_OF_BIRTH);
        LocalDate pastDate = LocalDate.parse(pastDateString, MainPage.DATE_FORMAT);
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .setDateOfBirth(date)
                .clickSave()
                .getProfileDateOfBirth();
        driver.quit();
        assertEquals(displayingDate, date);
    }

}