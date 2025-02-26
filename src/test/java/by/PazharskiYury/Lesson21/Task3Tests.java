package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.time.*;

import static org.testng.Assert.*;

public class Task3Tests {

    private static final int EXPECTED_ELEMENTS_COUNT = 42;
    private static final LocalDateTime EXPECTED_DATE_TIME = LocalDateTime
            .now(Clock.tickMinutes(ZoneId.systemDefault()))
            .plusDays(1)
            .withHour(23)
            .withMinute(11);

    private AndroidDriver androidDriver;
    private ApiDemosMainPage.ApiDemosViewsPage viewsPage;
    private ApiDemosMainPage.ApiDemosViewsPage.DateWidgetsPage.DialogPage dialogPage;


    @BeforeMethod
    private void setup() {
        AndroidDriverManager.setup(AndroidDriverManager.AndroidVirtualDevice.ELEMENT_COUNTER_DEVICE);
        androidDriver = AndroidDriverManager.getAndroidDriver();
        viewsPage = new ApiDemosMainPage(androidDriver).tapViews();
    }

    @AfterMethod
    private void tearDown() {
        AndroidDriverManager.quitDriver();
    }

    @Test
    private void allMenuElementsDisplayed() {
        WebElement lastElement = viewsPage.getDisplayedMenuElements().getLast();
        TouchControls testFinger = new TouchControls("Test Finger", androidDriver);
        testFinger.swipeUp(lastElement, 3000);
        WebElement afterSwipeLastElement = viewsPage.getDisplayedMenuElements().getLast();
        assertEquals(afterSwipeLastElement.getLocation(), lastElement.getLocation());
        assertEquals(afterSwipeLastElement.getText(), lastElement.getText());
        assertEquals(viewsPage.getDisplayedMenuElements().size(), EXPECTED_ELEMENTS_COUNT);
    }

    @Test
    private void changedDateIsDisplayed() {
        LocalDate expectedDate = EXPECTED_DATE_TIME.toLocalDate();
        dialogPage = viewsPage
                .tapDateWidgets()
                .tapDialog()
                .tapChangeTheDate()
                .chooseDate(expectedDate)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalDate(), expectedDate);
    }

    @Test(priority = 4)
    private void changedTimeIsDisplayed() {
        LocalTime timeToSet = LocalTime.of(23, 11);
        dialogPage
                .tapChangeTheTimeWithSpinner()
                .chooseTime(timeToSet)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalTime(), timeToSet);
    }

    @Test(priority = 5)
    private void changedDateTimeIsDisplayed() {
        assertEquals(dialogPage.getDisplayedDateTime(), EXPECTED_DATE_TIME);
    }

    @Test(priority = 6)
    private void changedDateTimeIsSaved() {
        LocalDateTime displayedDateTime = dialogPage.backToDateWidgetsPage().tapDialog().getDisplayedDateTime();
        assertEquals(displayedDateTime, EXPECTED_DATE_TIME);
    }

    @Test(priority = 7)
    private void textSwitcherNextButtonIncreasesDisplayedNumberByOne() {
        ApiDemosMainPage.ApiDemosViewsPage.TextSwitcherPage textSwitcherPage =
                dialogPage.backToDateWidgetsPage().backToViewsPage().tapTextSwitcher();
        int initialValue = textSwitcherPage.getDisplayedNumber();
        int fibbo = 0;
        int nacci = 1;
        int tapsCount;
        for (int i = 0; i < 6; i++) {
            tapsCount = fibbo + nacci;
            for (int j = tapsCount; j > 0; j--) {
                textSwitcherPage.tapNext();
            }
            fibbo = nacci;
            nacci = tapsCount;
            int displayedNumber = textSwitcherPage.getDisplayedNumber();
            assertEquals(displayedNumber, initialValue + tapsCount);
            initialValue = displayedNumber;
        }
    }

}