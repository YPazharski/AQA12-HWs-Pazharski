package by.PazharskiYury.Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Task2 {

    private static final String[] URLS = new String[] {
            "http://www.automationpractice.pl/index.php",
            "https://zoo.waw.pl/",
            "https://www.w3schools.com/",
            "https://www.clickspeedtester.com/click-counter/",
            "https://andersenlab.com/"
    };

    public static void run() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URLS[0]);
        for (int i = 1; i < URLS.length; i++) {
            driver.switchTo().newWindow(WindowType.WINDOW).get(URLS[i]);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            String title = driver.getTitle();
            System.out.println(title);
            System.out.println(driver.getCurrentUrl());
            if (title != null && title.contains("Zoo")) {
                driver.close();
            }
        }
    }

}