package by.PazharskiYury.Lesson17;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static by.PazharskiYury.Lesson17.TestHelper.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3Test {

    private static final By ACTIONS_ALERTS_IFRAMES_BUTTON = By.xpath("//div[normalize-space()='Actions, Alerts & Iframes']");
    private static final By BUTTONS_FRAME = By.cssSelector("iframe[title='Finish your registration']");
    private static final By RESULTS_MESSAGE = By.cssSelector("span.font-light.flex");

    private static final By CONFIRM_BUTTON = By.id("AlertButton");
    private static final String EXPECTED_CONFIRM_ALERT_TEXT = "You have called alert!";
    private static final String EXPECTED_CONFIRM_RESULTS_MESSAGE = "Congratulations, you have successfully enrolled in the course!";

    private static final By GET_DISCOUNT_BUTTON = By.xpath("//button[normalize-space()='Get Discount']");
    private static final String EXPECTED_GET_DISCOUNT_ALERT_TEXT = "Are you sure you want to apply the discount?";
    private static final String EXPECTED_GET_DISCOUNT_RESULTS_MESSAGE = "You received a 10% discount on the second course.";

    private static final By CANCEL_COURSE_BUTTON = By.xpath("//button[normalize-space()='Cancel course']");
    private static final String EXPECTED_CANCEL_COURSE_ALERT_TEXT =
            "Here you may describe a reason why you are cancelling your registration (or leave this field empty).";

    private static WebDriver browser;
    private static WebDriverWait wait;
    private static Alert alert;

    @BeforeSuite
    public void setup() {
        setupDriver();
    }

    @BeforeClass()
    public void configureBrowser() {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(2));
        loginAndExpandAQAButton(browser, wait);
        wait
                .until(visibilityOfElementLocated(ACTIONS_ALERTS_IFRAMES_BUTTON))
                .click();
        browser
                .switchTo()
                .frame(wait.until(visibilityOfElementLocated(BUTTONS_FRAME)));
    }

    @Test(priority = 1)
    public void clickingConfirmButtonInvokesAlertWithCorrectMessage() {
        wait
                .until(visibilityOfElementLocated(CONFIRM_BUTTON))
                .click();
        alert = browser.switchTo().alert();
        assertEquals(alert.getText(), EXPECTED_CONFIRM_ALERT_TEXT);
    }

    @Test(priority = 2)
    public void acceptingConfirmAlertDisplaysCorrectResultsMessage() {
        alert.accept();
        String actualMessage = wait.until(visibilityOfElementLocated(RESULTS_MESSAGE)).getText();
        assertEquals(actualMessage, EXPECTED_CONFIRM_RESULTS_MESSAGE);
    }

    @Test(priority = 3)
    public void doubleClickingGetDiscountButtonInvokesAlertWithCorrectMessage() {
        new Actions(browser)
                .doubleClick(wait.until(visibilityOfElementLocated(GET_DISCOUNT_BUTTON)))
                .build()
                .perform();
        alert = browser.switchTo().alert();
        assertEquals(alert.getText(), EXPECTED_GET_DISCOUNT_ALERT_TEXT);
    }

    @Test(priority = 4)
    public void acceptingGetDiscountAlertDisplaysCorrectResultsMessage() {
        alert.accept();
        String actualMessage = wait.until(visibilityOfElementLocated(RESULTS_MESSAGE)).getText();
        assertEquals(actualMessage, EXPECTED_GET_DISCOUNT_RESULTS_MESSAGE);
    }

    @Test(priority = 5)
    public void rightClickingCancelCourseButtonInvokesAlertWithCorrectMessage() {
        new Actions(browser)
                .contextClick(wait.until(visibilityOfElementLocated(CANCEL_COURSE_BUTTON)))
                .build()
                .perform();
        alert = browser.switchTo().alert();
        assertEquals(alert.getText(), EXPECTED_CANCEL_COURSE_ALERT_TEXT);
    }

    @Test(priority = 6)
    public void inputtingCancelCourseAlertMessageDisplaysThisMessage() {
        String cancelCourseAlertInput = "Test";
        alert.sendKeys(cancelCourseAlertInput);
        alert.accept();
        String actualMessage = wait.until(visibilityOfElementLocated(RESULTS_MESSAGE)).getText();
        assertTrue(actualMessage.contains(cancelCourseAlertInput));
    }

    @AfterClass
    public void quitBrowser() {
        browser.quit();
    }

}