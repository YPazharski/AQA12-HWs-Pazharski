package by.PazharskiYury.Lesson20;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/login";
    private final SelenideElement emailField = $(By.name("email"));
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement signInButton = $("button[type='submit']");

    private LoginPage() {
    }

    public static LoginPage open() {
        Selenide.open(URL);
        return new LoginPage();
    }

    public LoginPage inputEmail(String email) {
        emailField
                .setValue(email);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordField
                .setValue(password);
        return this;
    }

    public void signIn() {
        signInButton
                .click();
        signInButton.should(disappear);
    }

}