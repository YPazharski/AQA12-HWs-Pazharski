package by.PazharskiYury.Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {

    public static void printWebElementsInfo(@NotNull WebElement element1, @NotNull WebElement element2) {
        System.out.printf("%s is positioned higher on it's page than another.",
                element1.getLocation().y < element2.getLocation().y ? "Element 1"
                        : (element1.getLocation().y > element2.getLocation().y ? "Element 2" : "None of the elements"));

        System.out.println();
        System.out.printf("%s is positioned closer to the left side on it's page than another.",
                element1.getLocation().x < element2.getLocation().x ? "Element 1"
                        : (element1.getLocation().x > element2.getLocation().x ? "Element 2" : "None of the elements"));

        System.out.println();
        Dimension element1Size = element1.getSize();
        Dimension element2Size = element2.getSize();
        int element1Square = element1Size.height * element1Size.width;
        int element2Square = element2Size.height * element2Size.width;
        System.out.printf("%s has larger square on it's page than another.",
                element1Square > element2Square ? "Element 1"
                        : (element1Square < element2Square ? "Element 2" : "None of the elements"));

        System.out.println();
    }

    public static void runDemo() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().minimize();
        driver.get("https://www.google.com/");
        WebElement searchButton = driver.findElement(By.cssSelector("div[class='FPdoLc lJ9FBc'] input[name='btnK']"));
        WebElement luckyButton = driver.findElement(By.cssSelector("div[class='FPdoLc lJ9FBc'] input[name='btnI']"));
        Task3.printWebElementsInfo(searchButton, luckyButton);
        driver.close();
    }

}
