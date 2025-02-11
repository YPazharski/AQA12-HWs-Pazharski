package by.PazharskiYury;

import by.PazharskiYury.Lesson18.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class MainPageTests {

    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initNewDriver() {
        driver = new ChromeDriver();
    }

    @BeforeMethod(dependsOnMethods = "initNewDriver")
    public void openMainPage() {
        mainPage = MainPage.open(driver, EMAIL, PASSWORD);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}