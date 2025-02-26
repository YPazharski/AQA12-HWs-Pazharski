package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
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
        Actions builder = new Actions(driver);
        Action tap = builder
                .setActivePointer(PointerInput.Kind.TOUCH, name)
                .moveToElement(element)
                .click()
                .build();
        tap.perform();
    }

    public void swipeUp(WebElement element, int yOffset) {
        Actions builder = new Actions(driver);
        Action swapUp = builder
                .setActivePointer(PointerInput.Kind.TOUCH, name)
                .dragAndDropBy(element, 0, -yOffset)
                .build();
        swapUp.perform();
    }

}