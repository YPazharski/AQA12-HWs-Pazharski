package by.PazharskiYury.Lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TestHelper {

    private static final String URL = "https://qa-course-01.andersenlab.com/";
    private static final String EMAIL = "deepseek@nvidia.com";
    private static final By EMAIL_FIELD = By.name("email");
    private static final String PASSWORD = "qwerty12";
    private static final By PASSWORD_FIELD = By.name("password");
    private static final By SIGN_IN_BUTTON = By.cssSelector("button[type='submit']");
    public static final By AQA_PRACTICE_EXPAND_BUTTON = By.cssSelector("img[alt='Expand']");

    private static void login(@NotNull WebDriver browser,@NotNull WebDriverWait wait) {
        browser.get(URL);
        wait.until(presenceOfElementLocated(EMAIL_FIELD));
        browser
                .findElement(EMAIL_FIELD)
                .sendKeys(EMAIL);
        wait.until(presenceOfElementLocated(PASSWORD_FIELD));
        browser
                .findElement(PASSWORD_FIELD)
                .sendKeys(PASSWORD);
        wait.until(presenceOfElementLocated(SIGN_IN_BUTTON));
        browser
                .findElement(SIGN_IN_BUTTON)
                .click();
        wait.until(urlToBe(URL));
    }

    public static void loginAndExpandAQAButton(@NotNull WebDriver browser,@NotNull WebDriverWait wait) {
        login(browser, wait);
        wait.until(visibilityOfElementLocated(AQA_PRACTICE_EXPAND_BUTTON));
        browser
                .findElement(AQA_PRACTICE_EXPAND_BUTTON)
                .click();
    }

    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public static boolean elementIsLoadedAndVisible(@NotNull WebDriverWait wait, @NotNull By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }
        catch (StaleElementReferenceException | TimeoutException e) {
            return false;
        }
    }

}