package by.PazharskiYury.Lesson15;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class Task2 {

    private static final String[] URLS = new String[]{
            "http://www.automationpractice.pl/index.php",
            "https://zoo.waw.pl/",
            "https://www.w3schools.com/",
            "https://www.clickspeedtester.com/click-counter/",
            "https://andersenlab.com/"
    };

    public static void run() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);//some pages don't open properly in Belarus so this page load strategy is chosen
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        run(webDriver);
    }

    public static void run(@NotNull WebDriver webDriver) {
        webDriver.get(URLS[0]);
        for (int i = 1; i < URLS.length; i++) {
            webDriver.switchTo().newWindow(WindowType.WINDOW).get(URLS[i]);
        }

        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            webDriver.switchTo().window(windowHandle);
            String title = webDriver.getTitle();
            System.out.println(title);
            System.out.println(webDriver.getCurrentUrl());
            if (title != null && title.contains("Zoo")) {
                webDriver.close();
            }
        }
    }

}