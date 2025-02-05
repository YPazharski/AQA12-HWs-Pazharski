package by.PazharskiYury.Lesson17;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static by.PazharskiYury.Lesson17.TestHelper.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertTrue;

public class Task2Test {

    private static final By DRAG_AND_DROP_BUTTON = By.xpath("//div[normalize-space()='Drag & Drop']");
    private static WebDriver browser;
    private static WebDriverWait wait;

    @BeforeSuite
    public void setup() {
        setupDriver();
    }

    @BeforeMethod()
    public void configureBrowser() {
        browser = new ChromeDriver();
        browser.manage().window().minimize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(2));
    }

    @Test
    public void shouldDisplayCorrectMessageWhenDraggedAndDroppedCorrectly() {
        loginAndExpandAQAButton(browser, wait);
        WebElement dragAndDropButton = wait.until(visibilityOfElementLocated(DRAG_AND_DROP_BUTTON));
    }

    @AfterMethod
    public void quitBrowser() {
        browser.quit();
    }

    private static LocalDate getNextMondayDate(@NotNull LocalDate date) {
        do {
            date = date.plusDays(1);
        }
        while (date.getDayOfWeek() != DayOfWeek.MONDAY);

        return date;
    }

}