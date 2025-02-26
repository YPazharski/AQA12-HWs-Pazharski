package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class Task3Tests {

    private MainPage.ViewsPage.TextSwitcherPage textSwitcherPage;

    @BeforeClass
    public void setup() {
        AndroidDriverManager.setup();
        AndroidDriver androidDriver = AndroidDriverManager.getAndroidDriver();
        textSwitcherPage = new MainPage(androidDriver)
                .tapViews()
                .tapTextSwitcher();
    }

    @AfterClass
    public void tearDown() {
        AndroidDriverManager.quitDriver();
    }

    @Test
    public void textSwitcherNextButtonIncreasesDisplayedNumberByOne() {
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