package by.PazharskiYury.Lesson15;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {

    private static final String URL = "https://qa-course-01.andersenlab.com/login";
    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";

    public static void run() {
        WebDriver webDriver = new ChromeDriver();
        login(webDriver);
    }

    public static void run(@NotNull WebDriver webDriver) {
        login(webDriver);
    }

    public static void login(@NotNull WebDriver webDriver) {
        webDriver.get(URL);
        WebElement emailField = webDriver.findElement(By.cssSelector("input[placeholder='Enter email']"));
        WebElement passwordField = webDriver.findElement(By.cssSelector("input[placeholder='Enter password']"));
        WebElement signInButton = webDriver.findElement(By.cssSelector("button[type='submit']"));
        emailField.sendKeys(EMAIL);
        passwordField.sendKeys(PASSWORD);
        signInButton.click();
    }

}