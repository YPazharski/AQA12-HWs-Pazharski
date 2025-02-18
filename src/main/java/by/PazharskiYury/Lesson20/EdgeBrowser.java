package by.PazharskiYury.Lesson20;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
import io.github.bonigarcia.wdm.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowser {

    public static void setup() {
        Configuration.browser = "edge";
        Configuration.timeout = 2000;
    }

}