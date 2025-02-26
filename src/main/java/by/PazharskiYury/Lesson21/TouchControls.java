package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class TouchControls {

    private final AndroidDriver driver;
    private final String name;

    public TouchControls(String name, AndroidDriver androidDriver) {
        driver = androidDriver;
        this.name = name;
    }

    public void tap(WebElement element) {
        new Actions(driver)
                .setActivePointer(PointerInput.Kind.TOUCH, name)
                .moveToElement(element)
                .click()
                .build()
                .perform();
    }

    public void swipeUp(WebElement element, int yOffset) {
        new Actions(driver)
                .setActivePointer(PointerInput.Kind.TOUCH, name)
                .dragAndDropBy(element, 0, -yOffset)
                .build()
                .perform();
    }

}