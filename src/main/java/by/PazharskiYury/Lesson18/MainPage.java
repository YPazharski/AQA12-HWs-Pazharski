package by.PazharskiYury.Lesson18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/";
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage open(String accountEmail, String accountPassword) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        if (!loginPage.trySignIn(accountEmail, accountPassword)) {
            throw new FailedLoginException(accountEmail, accountPassword);
        }
        return this;
    }

}