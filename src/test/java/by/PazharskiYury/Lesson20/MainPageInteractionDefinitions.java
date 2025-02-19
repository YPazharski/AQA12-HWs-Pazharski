package by.PazharskiYury.Lesson20;

import com.codeborne.selenide.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.*;

public class MainPageInteractionDefinitions {

    private static final String EMAIL = "okuh@mail.ru";
    private static final String PASSWORD = "qwerty12";
    private MainPage mainPage;

    static {
        EdgeBrowser.setup();
    }

    @Given("I am authorized")
    public void iAmAuthorized() {
        mainPage = MainPage.open(EMAIL, PASSWORD);
        assertNotNull(mainPage);
    }

    @Given("I am on the Main Page")
    public void iAmOnTheMainPage() {
        assertEquals(WebDriverRunner.url(), MainPage.URL);
    }

    @When("I refresh Page")
    public void iRefreshPage() {
        Selenide.refresh();
    }

    @When("I click Sign out")
    public void iClickSignOut() {
        mainPage.signOut();
    }

    @Then("I am on the Login Page")
    public void iAmOnTheLoginPage() {
        assertEquals(WebDriverRunner.url(), LoginPage.URL);
    }

    @When("I click on Privacy policy")
    public void iClickOnPrivacyPolicy() {
        SelenideElement privacyPolicy = $("footer")
                .$(byText("Privacy Policy")).shouldBe(visible);
        Selenide.actions()
                .moveToElement(privacyPolicy)
                .build().perform();
        privacyPolicy.shouldBe(clickable).click();
        privacyPolicy.should(disappear);
    }

    @Then("I am on the Privacy policy page")
    public void iAmOnThePrivacyPolicyPage() {
        String currentUrl = WebDriverRunner.url();
        assertNotNull(currentUrl);
        assertTrue(currentUrl.toLowerCase().contains("privacy"));
        assertTrue(currentUrl.toLowerCase().contains("policy"));
    }
}