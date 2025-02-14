package by.PazharskiYury.Lesson19;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {

        public static void makeScreenshot(WebDriver driver, String fileName) {
            var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            var destination = new File("./target/screenshots/" + fileName + ".png");
            try {
                FileUtils.copyFile(screenshot, destination);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

}