package by.PazharskiYury.Lesson19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestLogListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestLogListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test {} start", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test {} fails.\n->Message: {}\n->Stack trace: {}",
                result.getName(), result.getThrowable().getMessage(), result.getThrowable().getStackTrace());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test {} successes", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test {} is skipped", result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.error("Test {} fails with timeout", result.getName());
    }

}