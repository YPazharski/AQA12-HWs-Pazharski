package by.PazharskiYury.Lesson21;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AndroidCalendar<I> {

    private final I invokeObject;
    private final AndroidDriver driver;
    private final TouchControls calendarFinger;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"1\")")
    private WebElement firstDate;

    @AndroidFindBy(accessibility = "Next month")
    private WebElement nextMonth;

    @AndroidFindBy(accessibility = "Previous month")
    private WebElement previousMonth;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement ok;

    private static final DateTimeFormatter CONTENT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.UK);

    public AndroidCalendar(I invokeObject, AndroidDriver androidDriver) {
        this.invokeObject = invokeObject;
        driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        calendarFinger = new TouchControls("Calendar Finger", driver);
    }

    public String getFirstVisibleDateValue() {
        return firstDate.getDomAttribute("content-desc");
    }

    public LocalDate getFirstVisibleDate() {
        return LocalDate.parse(getFirstVisibleDateValue(), CONTENT_DATE_FORMAT);
    }

    public AndroidCalendar<I> chooseDate(LocalDate dateToChoose) {
        LocalDate currentDate = getFirstVisibleDate();
        int currentMonthCount = currentDate.getYear() * 12 + currentDate.getMonthValue();
        int dateToChooseMonthCount = dateToChoose.getYear() * 12 + dateToChoose.getMonthValue();
        int monthDifference = dateToChooseMonthCount - currentMonthCount;
        WebElement changeMonth = monthDifference >= 0 ? nextMonth : previousMonth;
        monthDifference = Math.abs(monthDifference);
        while (monthDifference > 0) {
            calendarFinger.tap(changeMonth);
            monthDifference--;
        }
        String dateLocator = String.format("new UiSelector().text(\"%d\")", dateToChoose.getDayOfMonth());
        calendarFinger.tap(driver.findElement(AppiumBy.androidUIAutomator(dateLocator)));
        return this;
    }

    public I tapOk() {
        calendarFinger.tap(ok);
        return invokeObject;
    }

}