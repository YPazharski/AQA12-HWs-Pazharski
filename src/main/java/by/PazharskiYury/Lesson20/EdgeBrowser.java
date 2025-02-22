package by.PazharskiYury.Lesson20;

import com.codeborne.selenide.*;

public class EdgeBrowser {

    public static void setup() {
        Configuration.browser = "edge";
        Configuration.timeout = 2000;
    }

}