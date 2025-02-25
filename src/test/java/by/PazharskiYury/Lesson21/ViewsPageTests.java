package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.testng.Assert.*;

public class ViewsPageTests {

    private static final int EXPECTED_ELEMENTS_COUNT = 42;
    private AndroidDriver androidDriver;
    private ApiDemosMainPage.ApiDemosViewsPage viewsPage;

    @BeforeClass
    private void setup() {
        AndroidDriverManager.setup(AndroidDriverManager.AndroidVirtualDevice.ELEMENT_COUNTER_DEVICE);
        androidDriver = AndroidDriverManager.getAndroidDriver();
        viewsPage = new ApiDemosMainPage(androidDriver).tapViews();
    }

    @AfterClass
    private void tearDown() {
        AndroidDriverManager.quitDriver();
    }

    //@Test
    private void allMenuElementsDisplayed() {
        WebElement lastElement = viewsPage.getDisplayedMenuElements().getLast();
        TouchControls testFinger = new TouchControls("Test Finger", androidDriver);
        testFinger.swipeUp(lastElement, 3000);
        WebElement afterSwipeLastElement = viewsPage.getDisplayedMenuElements().getLast();
        assertEquals(afterSwipeLastElement.getLocation(), lastElement.getLocation());
        assertEquals(afterSwipeLastElement.getText(), lastElement.getText());
    }

    //@Test(dependsOnMethods = "allMenuElementsDisplayed")
    private void menuElementCountMatchesExpected() {
        assertEquals(viewsPage.getDisplayedMenuElements().size(), EXPECTED_ELEMENTS_COUNT);
    }

    //@Test(dependsOnMethods = "menuElementCountMatchesExpected")
    private void changedDateIsDisplayed() {
        LocalDate dateToSet = LocalDate.now().plusDays(1);
        ApiDemosMainPage.ApiDemosViewsPage.DateWidgetsPage.DialogPage dialogPage = viewsPage
                .tapDateWidgets()
                .tapDialog()
                .tapChangeTheDate()
                .chooseDate(dateToSet)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalDate(), dateToSet);
    }

    //@Test(dependsOnMethods = "menuElementCountMatchesExpected")
    private void changedTimeIsDisplayed() {
        LocalTime timeToSet = LocalTime.of(23, 11);
        ApiDemosMainPage.ApiDemosViewsPage.DateWidgetsPage.DialogPage dialogPage = viewsPage
                .tapDateWidgets()
                .tapDialog()
                .tapChangeTheTimeWithSpinner()
                .chooseTime(timeToSet)
                .tapOk();
        assertEquals(dialogPage.getDisplayedDateTime().toLocalTime(), timeToSet);
    }

}