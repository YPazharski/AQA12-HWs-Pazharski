package by.PazharskiYury.Lesson18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final String URL = "https://qa-course-01.andersenlab.com/login";
    private final WebDriver driver;

    @FindBy(css = "input[placeholder='Enter email']")
    private WebElement emailField;

    @FindBy(css = "input[placeholder='Enter password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public LoginPage signIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return this;
    }

}