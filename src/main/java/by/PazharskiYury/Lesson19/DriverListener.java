package by.PazharskiYury.Lesson19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverListener implements WebDriverListener {

    private final WebDriver driver;

    public DriverListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        ScreenshotMaker.makeScreenshot(driver, "error-" + System.currentTimeMillis());
    }

}