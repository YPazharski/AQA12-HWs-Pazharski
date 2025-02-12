package by.PazharskiYury.Lesson18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;

public class Lesson18Main {

    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";

    public static void main(String[] args) throws InterruptedException {
        LocalDate newDate = LocalDate.of(1996, 12, 20);
        LocalDate altNewDate = LocalDate.of(1997, 6, 15);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        var editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton()
                .selectDateOfBirth(newDate);
    }

}