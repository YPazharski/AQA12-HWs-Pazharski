package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {

    private static AndroidDriver androidDriver;

    public static void setup(AndroidVirtualDevice androidVirtualDevice) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(androidVirtualDevice.getName())
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
        androidDriver.quit();
        androidDriver = null;
    }

    public enum AndroidVirtualDevice {
        PIXEL9_PRO_XL("Pixel 9 Pro XL API 35"),
        ELEMENT_COUNTER_DEVICE("ElementCounterDevice");

        private final String name;

        AndroidVirtualDevice(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}