package by.PazharskiYury.Lesson19;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus().equals(Status.FAILED) || result.getStatus().equals(Status.BROKEN)) {
            makeScreenshot();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) SingleWebDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}