package by.PazharskiYury.Lesson21;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ApiDemosMainPage {

    AndroidDriver driver;
    TouchControls forefinger;

    @AndroidFindBy(accessibility = "Views")
    private WebElement views;

    public ApiDemosMainPage(AndroidDriver androidDriver) {
        driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        forefinger = new TouchControls("forefinger", driver);
    }

    public ApiDemosViewsPage tapViews() {
        forefinger.tap(views);
        return new ApiDemosViewsPage();
    }

    public class ApiDemosViewsPage {

        @AndroidFindBys(value = @AndroidBy(xpath = "//android.widget.ListView[@resource-id=\"android:id/list\"]/*"))
        private List<WebElement> displayedMenuElements;

        @AndroidFindBy(accessibility = "Date Widgets")
        private WebElement dateWidgets;

        @AndroidFindBy(accessibility = "TextSwitcher")
        private WebElement textSwitcher;

        private ApiDemosViewsPage() {
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        }

        public List<WebElement> getDisplayedMenuElements() {
            return displayedMenuElements;
        }

        public DateWidgetsPage tapDateWidgets() {
            forefinger.tap(dateWidgets);
            return new DateWidgetsPage();
        }

        public TextSwitcherPage tapTextSwitcher() {
            forefinger.tap(textSwitcher);
            return new TextSwitcherPage();
        }

        public class TextSwitcherPage {

            @AndroidFindBy(accessibility = "Next")
            private WebElement next;

            @AndroidFindBy(xpath = "//android.widget.TextSwitcher[@resource-id=\"io.appium.android.apis:id/switcher\"]/*")
            private WebElement countDisplay;

            private TextSwitcherPage() {
                PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
            }

            public TextSwitcherPage tapNext() {
                forefinger.tap(next);
                return this;
            }

            public int getDisplayedNumber() {
                return Integer.parseInt(countDisplay.getText());
            }

        }

        public class DateWidgetsPage {

            @AndroidFindBy(accessibility = "1. Dialog")
            private WebElement dialog;

            private DateWidgetsPage() {
                PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
            }

            public DialogPage tapDialog() {
                forefinger.tap(dialog);
                return new DialogPage();
            }

            public ApiDemosViewsPage backToViewsPage() {
                driver.navigate().back();
                return ApiDemosViewsPage.this;
            }

            public class DialogPage {

                @AndroidFindBy(id = "io.appium.android.apis:id/dateDisplay")
                private WebElement dateTimeDisplay;

                @AndroidFindBy(accessibility = "change the date")
                private WebElement changeTheDate;

                @AndroidFindBy(accessibility = "change the time (spinner)")
                private WebElement changeTheTimeSpinner;

                public final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("M-d-yyyy HH:mm", Locale.UK);

                private DialogPage() {
                    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
                }

                public LocalDateTime getDisplayedDateTime() {
                    return LocalDateTime.parse(getDisplayedDateTimeValue(), DATE_TIME_FORMAT);
                }

                public String getDisplayedDateTimeValue() {
                    return dateTimeDisplay.getText();
                }

                public AndroidCalendar<DialogPage> tapChangeTheDate() {
                    forefinger.tap(changeTheDate);
                    return new AndroidCalendar<DialogPage>(this, driver);
                }

                public AndroidTimeSpinner<DialogPage> tapChangeTheTimeWithSpinner() {
                    forefinger.tap(changeTheTimeSpinner);
                    return new AndroidTimeSpinner<DialogPage>(this, driver);
                }

                public DateWidgetsPage backToDateWidgetsPage() {
                    driver.navigate().back();
                    return DateWidgetsPage.this;
                }

            }

        }

    }

}