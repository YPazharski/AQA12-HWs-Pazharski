package by.PazharskiYury.Lesson20;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static final String URL = "https://qa-course-01.andersenlab.com/";

    private final SelenideElement signOutButton = $("img[alt='Sign Out']");

    private MainPage() {
    }

    @Nullable
    public static MainPage open(String email, String password) {
        LoginPage
                .open()
                .inputEmail(email)
                .inputPassword(password)
                .signIn();
        return Objects.equals(WebDriverRunner.url(), URL) ? new MainPage() : null;
    }

    public void signOut() {
        signOutButton
                .shouldBe(clickable)
                .click();
        signOutButton.should(disappear);
    }

}