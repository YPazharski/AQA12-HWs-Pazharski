package by.PazharskiYury.Lesson15;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Task5 {

    private static final File PROFILE_IMAGE = new File("src/main/resources/Dog.png");
    private static final By SEND_IMAGE_FIELD = By.cssSelector("input[type='file'][accept='image/*']");
    private static final By CLOSE_WINDOW_BUTTON = By.cssSelector("img[src='/static/media/close.9f94234648796ff1f1fc3a468c029c43.svg']");

    public static void run() {
        WebDriver webDriver = new ChromeDriver();
        run(webDriver);
    }

    public static void run(@NotNull WebDriver webDriver) {
        Task4.login(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(SEND_IMAGE_FIELD));
        webDriver
                .findElement(SEND_IMAGE_FIELD)
                .sendKeys(PROFILE_IMAGE.getAbsolutePath());
        wait.until(ExpectedConditions.presenceOfElementLocated(CLOSE_WINDOW_BUTTON));
        webDriver
                .findElement(CLOSE_WINDOW_BUTTON)
                .click();
    }

}
