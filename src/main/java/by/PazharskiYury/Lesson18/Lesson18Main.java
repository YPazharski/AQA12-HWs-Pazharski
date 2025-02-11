package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson18Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open("deepseek@nvidia.com", "qwerty12");
    }

}