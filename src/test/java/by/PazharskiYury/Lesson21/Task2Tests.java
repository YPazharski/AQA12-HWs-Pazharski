package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;
import java.time.*;

import static org.testng.Assert.assertEquals;

public class Task2Tests {

    private static final LocalDateTime EXPECTED_DATE_TIME = LocalDateTime
            .now(Clock.tickMinutes(ZoneId.systemDefault()))
            .plusDays(1)
            .withHour(23)
            .withMinute(11);

    private MainPage.ViewsPage.DateWidgetsPage.DialogPage dialogPage;

    @BeforeClass
    public void setup() {
        AndroidDriverManager.setup(AndroidDriverManager.AndroidVirtualDevice.ELEMENT_COUNTER_DEVICE);
        AndroidDriver androidDriver = AndroidDriverManager.getAndroidDriver();
        dialogPage = new MainPage(androidDriver)
                .tapViews()
                .tapDateWidgets()
                .tapDialog();
    }

    @AfterClass
    public void tearDown() {
        AndroidDriverManager.quitDriver();
    }

    @Test
    public void changedDateIsDisplayed() {
        LocalDate expectedDate = EXPECTED_DATE_TIME.toLocalDate();
        dialogPage
                .tapChangeTheDate()
                .chooseDate(expectedDate)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalDate(), expectedDate);
    }

    @Test
    public void changedTimeIsDisplayed() {
        LocalTime timeToSet = EXPECTED_DATE_TIME.toLocalTime();
        dialogPage
                .tapChangeTheTimeWithSpinner()
                .chooseTime(timeToSet)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalTime(), timeToSet);
    }

    @Test(dependsOnMethods = {"changedDateIsDisplayed", "changedTimeIsDisplayed"})
    public void changedDateTimeIsDisplayed() {
        assertEquals(dialogPage.getDisplayedDateTime(), EXPECTED_DATE_TIME);
    }

    @Test(dependsOnMethods = "changedDateTimeIsDisplayed")
    public void changedDateTimeIsSaved() {
        LocalDateTime displayedDateTime = dialogPage.backToDateWidgetsPage().tapDialog().getDisplayedDateTime();
        assertEquals(displayedDateTime, EXPECTED_DATE_TIME);
    }

}