package by.PazharskiYury.Lesson17;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static by.PazharskiYury.Lesson17.TestHelper.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;

public class Task1Test {

    private static final By SELECT_BUTTON = By.cssSelector("div[class='flex flex-col text-sm text-black bg-white shadow-custom absolute mt-12 cursor-pointer'] div:nth-child(1)");
    private static final By COUNTRY_SELECTOR = By.cssSelector("select[title='Select country']");
    private static final String COUNTRY_SELECTION = "USA";
    private static final By LANGUAGE_SELECTOR = By.cssSelector("select[title='Select language']");
    private static final String LANGUAGE_SELECTION = "English";
    private static final By TYPE_SELECTOR = By.cssSelector("select[title='Select type']");
    private static final String TYPE_SELECTION = "Testing";
    private static final By START_DATE_INPUT = By.cssSelector("input[title='Start date']");
    private static final By END_DATE_INPUT = By.cssSelector("input[title='End date']");
    private static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final By COURSES_SELECTOR = By.cssSelector("#MultipleSelect");
    private static final String[] COURSES_SELECTIONS = {
            "AQA Java",
            "AQA Python"
    };
    private static final By SEARCH_BUTTON = By.name("SelectPageSearchButton");
    private static final By EXPECTED_SEARCH_RESULTS = By.xpath("//*[contains(text(), 'Unfortunately, we did not find any courses matching your chosen criteria.')]");
    private static WebDriver browser;
    private static WebDriverWait wait;

    @BeforeSuite
    public void setup() {
        setupDriver();
    }

    @BeforeMethod()
    public void configureBrowser() {
        browser = new ChromeDriver();
        browser.manage().window().minimize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(2));
    }

    @Test
    public void shouldDisplayCorrectMessageWhenCoursesNotFound() {
        loginAndExpandAQAButton(browser, wait);
        wait.until(visibilityOfElementLocated(SELECT_BUTTON));
        browser
                .findElement(SELECT_BUTTON)
                .click();
        wait.until(visibilityOfElementLocated(COUNTRY_SELECTOR));
        Select countrySelector = new Select(browser.findElement(COUNTRY_SELECTOR));
        countrySelector.selectByVisibleText(COUNTRY_SELECTION);
        wait.until(visibilityOfElementLocated(LANGUAGE_SELECTOR));
        Select languageSelector = new Select(browser.findElement(LANGUAGE_SELECTOR));
        languageSelector.selectByVisibleText(LANGUAGE_SELECTION);
        wait.until(visibilityOfElementLocated(TYPE_SELECTOR));
        Select typeSelector = new Select(browser.findElement(TYPE_SELECTOR));
        typeSelector.selectByVisibleText(TYPE_SELECTION);
        wait.until(visibilityOfElementLocated(START_DATE_INPUT));
        WebElement startDateInput = browser.findElement(START_DATE_INPUT);
        LocalDate nextMonday = getNextMondayDate(LocalDate.now());
        startDateInput.sendKeys(nextMonday.format(DATE_INPUT_FORMAT));
        wait.until(visibilityOfElementLocated(END_DATE_INPUT));
        WebElement endDateInput = browser.findElement(END_DATE_INPUT);
        endDateInput.sendKeys(nextMonday.plusWeeks(2).format(DATE_INPUT_FORMAT));
        wait.until(visibilityOfElementLocated(COURSES_SELECTOR));
        Select coursesSelector = new Select(browser.findElement(COURSES_SELECTOR));
        Arrays
                .stream(COURSES_SELECTIONS)
                .forEach(coursesSelector::selectByVisibleText);
        wait.until(visibilityOfElementLocated(SEARCH_BUTTON));
        browser
                .findElement(SEARCH_BUTTON)
                .click();
        assertTrue(elementIsLoadedAndVisible(browser, wait, EXPECTED_SEARCH_RESULTS));
    }

    @AfterMethod
    public void quitBrowser() {
        browser.quit();
    }

    private static LocalDate getNextMondayDate(@NotNull LocalDate date) {
        do {
            date = date.plusDays(1);
        }
        while (date.getDayOfWeek() != DayOfWeek.MONDAY);

        return date;
    }

}