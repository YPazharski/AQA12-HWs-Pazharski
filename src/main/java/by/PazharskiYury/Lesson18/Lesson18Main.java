package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson18Main {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        MainPage
                .open(driver, "okuh@mail.ru", "qwerty12")
                .clickEditProfileButton()
                .setFieldValue(EditProfileField.FIRST_NAME, "ooo")
                .clickSave()
                .clickEditProfileButton();
    }

}