package by.PazharskiYury.Lesson15;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task1 {

    private static final By PRIVACY_POLICY = By.xpath("//*[text()='Privacy Policy']");
    private static final By PROFILE_EDIT_BUTTON = By.cssSelector("img[alt='Edit']");
    private static final By EDIT_FIRST_NAME_FIELD = By.name("firstName");
    private static final By EDIT_LAST_NAME_FIELD = By.name("lastName");
    private static final By EDIT_SAVE_BUTTON = By.cssSelector("button[type='submit']");

    public static void run(@NotNull WebDriver driver) {
        maimn0(driver);
        maimn1(driver);
        maipe0(driver);
        maipe1(driver);
    }

    private static void maimn0(@NotNull WebDriver driver) {
        Task4.login(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(PRIVACY_POLICY));
        driver
                .findElement(PRIVACY_POLICY)
                .click();
    }

    private static void maimn1(@NotNull WebDriver driver) {
        Task4.login(driver);
        driver.navigate().refresh();
    }

    private static void maipe0(@NotNull WebDriver driver) {
        editProfileField(driver, EDIT_FIRST_NAME_FIELD, "Name", "Noname");
    }

    private static void maipe1(@NotNull WebDriver driver) {
        editProfileField(driver, EDIT_LAST_NAME_FIELD, "Same", "Nosame");
    }

    private static void editProfileField(@NotNull WebDriver driver, @NotNull By editFieldLocator, @NotNull String value, @NotNull String altValue) {
        Task4.login(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(PROFILE_EDIT_BUTTON));
        driver
                .findElement(PROFILE_EDIT_BUTTON)
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(editFieldLocator));
        WebElement editField = driver.findElement(editFieldLocator);
        String currentValue = editField.getDomAttribute("value");
        editField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editField.sendKeys(Keys.BACK_SPACE);
        editField.sendKeys((currentValue == null || !currentValue.equals(value)) ? value : altValue);
        wait.until(ExpectedConditions.presenceOfElementLocated(EDIT_SAVE_BUTTON));
        driver
                .findElement(EDIT_SAVE_BUTTON)
                .click();
    }

}