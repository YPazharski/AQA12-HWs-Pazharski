package by.PazharskiYury.Lesson21;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

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
        @AndroidFindBy(accessibility = "WebView3")
        private WebElement webView3;

        @AndroidFindBy(accessibility = "Animation")
        private WebElement animation;

        @AndroidFindBy(accessibility = "ImageView")
        private WebElement imageView;

        @AndroidFindBy(accessibility = "Layout Animation")
        private WebElement layoutAnimation;

        @AndroidFindBys(value = @AndroidBy(xpath = "//android.widget.ListView[@resource-id=\"android:id/list\"]/*"))
        private List<WebElement> displayedMenuElements;

        private ApiDemosViewsPage() {
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        }

        public void tapWebView3() {
            forefinger.tap(webView3);
        }

        public void tapAnimation() {
            forefinger.tap(animation);
        }

        public WebElement getImageView() {
            return imageView;
        }

        public WebElement getLayoutAnimation() {
            return layoutAnimation;
        }

        public List<WebElement> getDisplayedMenuElements() {
            return displayedMenuElements;
        }
    }

}