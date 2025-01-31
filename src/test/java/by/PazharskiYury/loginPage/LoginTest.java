package by.PazharskiYury.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {

    public static final String LOGIN_PAGE = "https://qa-course-01.andersenlab.com/login";
    public static final String EXPECTED_URL = "https://qa-course-01.andersenlab.com/";
    public static final By EMAIL_FIELD = By.name("email");
    public static final By PASSWORD_FIELD = By.name("password");
    public static final By SIGN_IN_BUTTON = By.cssSelector("button[type='submit']");
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setupTests() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().minimize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @BeforeMethod
    public void getToLoginPage() {
        driver.get(LOGIN_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_BUTTON));
        emailField = driver.findElement(EMAIL_FIELD);
        passwordField = driver.findElement(PASSWORD_FIELD);
        signInButton = driver.findElement(SIGN_IN_BUTTON);
    }

    @AfterMethod
    public void closeDriver() {
        driver.manage().deleteAllCookies();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

    @DataProvider(name = "valid account data")
    public Object[][] getTestData() {
        return new String[][]{
                {"red@rockstar.com", "qwerty12"},
                {"dead@rockstar.com", "qwerty12"},
                {"redemption@rockstar.com", "qwerty12"}
        };
    }

    @Test(dataProvider = "valid account data")
    public void shouldGetToMainPageUsingValidAccountData(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        wait.until(ExpectedConditions.invisibilityOf(signInButton));
        assertEquals(driver.getCurrentUrl(), EXPECTED_URL);
    }

}