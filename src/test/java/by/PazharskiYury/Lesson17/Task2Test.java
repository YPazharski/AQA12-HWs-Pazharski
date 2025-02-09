package by.PazharskiYury.Lesson17;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

import static by.PazharskiYury.Lesson17.TestHelper.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertTrue;

public class Task2Test {

    private static final By DRAG_AND_DROP_BUTTON = By.xpath("//div[normalize-space()='Drag & Drop']");
    private static final By WRITE_TEST_CASES_BLOCK = By.id("manual1");
    private static final By TESTING_REQUIREMENTS_BLOCK = By.id("manual2");
    private static final By WRITE_AUTOMATION_SCRIPTS_BLOCK = By.id("auto1");
    private static final By FRAMEWORK_SET_UP_BLOCK = By.id("auto2");
    private static final By MANUAL_WORK_LEFT_CELL = By.id("target-manual1");
    private static final By MANUAL_WORK_RIGHT_CELL = By.id("target-manual2");
    private static final By AUTOMATION_WORK_LEFT_CELL = By.id("target-auto1");
    private static final By AUTOMATION_WORK_RIGHT_CELL = By.id("target-auto2");
    private static final By EXPECTED_MESSAGE = By.xpath("""
            //*[contains(text(), "Congratulations! Let's test for the best!")]""");
    private static WebDriver browser;
    private static WebDriverWait wait;

    @BeforeSuite
    public void setup() {
        setupDriver();
    }

    @BeforeMethod()
    public void configureBrowser() {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(2));
    }

    @Test
    public void shouldDisplayCorrectMessageWhenDraggedAndDroppedCorrectly() {
        loginAndExpandAQAButton(browser, wait);
        wait
                .until(visibilityOfElementLocated(DRAG_AND_DROP_BUTTON))
                .click();
        performDragAndDropBy(WRITE_TEST_CASES_BLOCK, MANUAL_WORK_LEFT_CELL);
        performDragAndDropBy(TESTING_REQUIREMENTS_BLOCK, MANUAL_WORK_RIGHT_CELL);
        performDragAndDropBy(WRITE_AUTOMATION_SCRIPTS_BLOCK, AUTOMATION_WORK_LEFT_CELL);
        performDragAndDropBy(FRAMEWORK_SET_UP_BLOCK, AUTOMATION_WORK_RIGHT_CELL);
        assertTrue(elementIsLoadedAndVisible(wait, EXPECTED_MESSAGE));
    }

    private static void performDragAndDropBy(@NotNull By source, @NotNull By target) {
        new Actions(browser)
                .dragAndDrop(wait.until(visibilityOfElementLocated(source))
                        , wait.until(visibilityOfElementLocated(target)))
                .build()
                .perform();
    }

    @AfterMethod
    public void quitBrowser() {
        browser.quit();
    }

}