package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson18Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        new LoginPage(driver)
                .open()
                .signIn("deepseek@nvidia.com", "qwerty12");
    }

}