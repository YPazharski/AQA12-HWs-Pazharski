package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AndroidTimeSpinner<I> {

    private I invokeObject;
    private AndroidDriver driver;
    private TouchControls timeFinger;

    @AndroidFindBys(value = @AndroidBy(xpath = "//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[1]/*"))
    private List<WebElement> hourSpinner;

    @AndroidFindBys(value = @AndroidBy(xpath = "//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.LinearLayout/android.widget.NumberPicker[2]/*"))
    private List<WebElement> minuteSpinner;

    @AndroidFindBys(value = @AndroidBy(xpath = "//android.widget.LinearLayout[@resource-id=\"android:id/timePickerLayout\"]/android.widget.NumberPicker/*"))
    private List<WebElement> timeOfDaySelection;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"AM\")")
    private WebElement am;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"PM\")")
    private WebElement pm;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement ok;

    public AndroidTimeSpinner(I invokeObject, AndroidDriver androidDriver) {
        this.invokeObject = invokeObject;
        driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        timeFinger = new TouchControls("Time Finger", driver);
    }

    private WebElement getTopHour() {
        return hourSpinner.get(0);
    }

    private WebElement getCenterHour() {
        return hourSpinner.get(1);
    }

    private WebElement getBottomHour() {
        return hourSpinner.get(2);
    }

    private WebElement getTopMinute() {
        return minuteSpinner.get(0);
    }

    private WebElement getCenterMinute() {
        return minuteSpinner.get(1);
    }

    private WebElement getBottomMinute() {
        return minuteSpinner.get(2);
    }

    public AndroidTimeSpinner<I> chooseTime(LocalTime timeToChoose) {
        int hour = timeToChoose.getHour();
        String dayPart = "AM";
        if (hour > 11) {
            hour -= 12;
            dayPart = "PM";
        }
        if (hour == 0) {
            hour = 12;
        }
        int minute = timeToChoose.getMinute();
        chooseMinute(minute);
        chooseHour(hour);
        chooseDayPart(dayPart);
        return this;
    }

    public I tapOk() {
        timeFinger.tap(ok);
        return invokeObject;
    }

    private void chooseDayPart(String dayPartToChoose) {
        WebElement elementToTap = dayPartToChoose.equals("AM") ? am : pm;
        timeFinger.tap(elementToTap);
    }

    private void chooseHour(int hourToChoose) {
        String currentHourValue = getCenterHour().getText();
        int currentMinute = Integer.parseInt(currentHourValue);
        int topTaps = (12 + currentMinute - hourToChoose) % 12;
        int downTaps = (12 + hourToChoose - currentMinute) % 12;
        int tapsCount = topTaps;
        WebElement elementToTap;
        if (topTaps <= downTaps) {
            elementToTap = getTopHour();
        } else {
            elementToTap = getBottomHour();
            tapsCount = downTaps;
        }
        while (tapsCount > 0) {
            timeFinger.tap(elementToTap);
            tapsCount--;
        }
    }

    private void chooseMinute(int minuteToChoose) {
        String currentMinuteValue = getCenterMinute().getText();
        int currentMinute = Integer.parseInt(currentMinuteValue);
        int topTaps = (60 + currentMinute - minuteToChoose) % 60;
        int downTaps = (60 + minuteToChoose - currentMinute) % 60;
        int tapsCount = topTaps;
        WebElement elementToTap;
        if (topTaps <= downTaps) {
            elementToTap = getTopMinute();
        } else {
            elementToTap = getBottomMinute();
            tapsCount = downTaps;
        }
        while (tapsCount > 0) {
            timeFinger.tap(elementToTap);
            tapsCount--;
        }
    }

}