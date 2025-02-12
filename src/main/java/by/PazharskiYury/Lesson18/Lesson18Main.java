package by.PazharskiYury.Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
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
        var editProfileForm = MainPage
                .open(driver, EMAIL, PASSWORD)
                .clickEditProfileButton();
        String pastDateString = editProfileForm
                .getFieldValue(EditProfileField.DATE_OF_BIRTH);
        LocalDate pastDate = LocalDate.parse(pastDateString, MainPage.DATE_FORMAT);
        LocalDate date = pastDate.equals(newDate) ? altNewDate : newDate;
        LocalDate displayingDate = editProfileForm
                .setDateOfBirth(date)
                .clickSave()
                .getProfileDateOfBirth();
        System.out.println(displayingDate.format(MainPage.DATE_FORMAT));
    }

}