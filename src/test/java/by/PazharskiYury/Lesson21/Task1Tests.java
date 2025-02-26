package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class Task1Tests {

    private static final int EXPECTED_ELEMENTS_COUNT = 42;

    private AndroidDriver androidDriver;
    private ApiDemosMainPage.ApiDemosViewsPage viewsPage;

    @BeforeClass
    public void setup() {
        AndroidDriverManager.setup(AndroidDriverManager.AndroidVirtualDevice.ELEMENT_COUNTER_DEVICE);
        androidDriver = AndroidDriverManager.getAndroidDriver();
        viewsPage = new ApiDemosMainPage(androidDriver).tapViews();
    }

    @AfterClass
    public void tearDown() {
        AndroidDriverManager.quitDriver();
    }

    @Test
    public void allMenuElementsDisplayed() {
        WebElement lastElement = viewsPage.getDisplayedMenuElements().getLast();
        TouchControls testFinger = new TouchControls("Test Finger", androidDriver);
        testFinger.swipeUp(lastElement, 3000);
        WebElement afterSwipeLastElement = viewsPage.getDisplayedMenuElements().getLast();
        assertEquals(afterSwipeLastElement.getLocation(), lastElement.getLocation());
        assertEquals(afterSwipeLastElement.getText(), lastElement.getText());
    }

    @Test
    public void elementCountMatchesExpected() {
        assertEquals(viewsPage.getDisplayedMenuElements().size(), EXPECTED_ELEMENTS_COUNT);
    }

}