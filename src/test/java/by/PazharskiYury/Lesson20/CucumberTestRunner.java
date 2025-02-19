package by.PazharskiYury.Lesson20;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@SuppressWarnings("NewClassNamingConvention")
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json"},
        features = {"src/test/resources/Lesson20/features/mainPageInteraction.feature"},
        glue = {"by.PazharskiYury.Lesson20"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}