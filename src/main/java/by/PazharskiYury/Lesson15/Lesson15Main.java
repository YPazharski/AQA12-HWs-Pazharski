package by.PazharskiYury.Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson15Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().minimize();
        Task2.run();
        Task3.runDemo(driver);
        Task4.run(driver);
        Task5.run(driver);
    }

}