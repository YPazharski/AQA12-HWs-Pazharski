package by.PazharskiYury.Lesson18;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.*;

import java.time.Duration;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class LoginPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/login";
    private final WebDriver driver;

    @FindBy(css = "input[placeholder='Enter email']")
    private WebElement emailField;

    @FindBy(css = "input[placeholder='Enter password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    private LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть страницу авторизации")
    public static LoginPage open(WebDriver driver) {
        driver.get(URL);
        return new LoginPage(driver);
    }

    public boolean trySignIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        try {
            Allure.step(String.format("Производится авторизация с email \"%s\" и паролем \"%s\"...", email, password));
            return new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.urlToBe(MainPage.URL));
        } catch (TimeoutException e) {
            Allure.step(String.format("Авторизация с email \"%s\" и паролем \"%s\" не удалась", email, password));
            return false;
        }
    }

}