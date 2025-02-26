package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {

    private static AndroidDriver androidDriver;

    public static void setup() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("ElementCounterDevice")
                .setPlatformName(Platform.ANDROID.name())
                .setAppPackage("io.appium.android.apis")
                .setAppActivity(".ApiDemos");
        androidDriver = new AndroidDriver(getAppiumServerUrl(), options);
    }

    private static URL getAppiumServerUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Nullable
    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public static void quitDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
    }

}