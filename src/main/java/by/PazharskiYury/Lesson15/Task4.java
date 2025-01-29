package by.PazharskiYury.Lesson15;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task4 {

    private static final String URL = "https://qa-course-01.andersenlab.com/login";
    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";
    private static final By EMAIL_FIELD = By.cssSelector("input[placeholder='Enter email']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[placeholder='Enter password']");
    private static final By SIGN_IN_BUTTON = By.cssSelector("button[type='submit']");

    public static void run() {
        WebDriver webDriver = new ChromeDriver();
        login(webDriver);
    }

    public static void run(@NotNull WebDriver webDriver) {
        login(webDriver);
    }

    public static void login(@NotNull WebDriver webDriver) {
        webDriver.get(URL);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_FIELD));
        webDriver
                .findElement(EMAIL_FIELD)
                .sendKeys(EMAIL);
        wait.until(ExpectedConditions.presenceOfElementLocated(PASSWORD_FIELD));
        webDriver
                .findElement(PASSWORD_FIELD)
                .sendKeys(PASSWORD);
        wait.until(ExpectedConditions.presenceOfElementLocated(SIGN_IN_BUTTON));
        webDriver
                .findElement(SIGN_IN_BUTTON)
                .click();
    }

}