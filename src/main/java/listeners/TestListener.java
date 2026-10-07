package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import drivers.DriverFactory;
import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.LoggerUtil;

public class TestListener implements ITestListener {

    private final Logger logger =
            LoggerUtil.getLogger(TestListener.class);

    private final ExtentReports extent =
            ExtentManager.getInstance();

    @Override
    public void onStart(ITestContext context) {

        logger.info(
                "TEST SUITE STARTED: {}",
                context.getName()
        );
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod()
                       .getMethodName();

        ExtentTest test =
                extent.createTest(testName);

        ExtentTestManager.setTest(test);

        test.log(
                Status.INFO,
                "Test execution started"
        );

        logger.info(
                "STARTING TEST: {}",
                testName
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName =
                result.getMethod()
                       .getMethodName();

        ExtentTest test =
                ExtentTestManager.getTest();

        test.log(
                Status.PASS,
                "Test passed successfully"
        );

        logger.info(
                "TEST PASSED: {}",
                testName
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =
                result.getMethod()
                       .getMethodName();

        ExtentTest test =
                ExtentTestManager.getTest();

        test.log(
                Status.FAIL,
                "Test failed"
        );

        test.log(
                Status.FAIL,
                result.getThrowable()
        );

        logger.error(
                "TEST FAILED: {}",
                testName
        );

        logger.error(
                "FAILURE REASON",
                result.getThrowable()
        );

        String screenshotPath =
                captureScreenshot(result);

        if (screenshotPath != null) {

            try {

                test.addScreenCaptureFromPath(
                        screenshotPath
                );

            } catch (Exception e) {

                logger.error(
                        "Unable to attach screenshot",
                        e
                );
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test =
                ExtentTestManager.getTest();

        if (test != null) {

            test.log(
                    Status.SKIP,
                    "Test skipped"
            );

            if (result.getThrowable() != null) {

                test.log(
                        Status.SKIP,
                        result.getThrowable()
                );
            }
        }

        logger.warn(
                "TEST SKIPPED: {}",
                result.getMethod()
                       .getMethodName()
        );
    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info(
                "TEST SUITE FINISHED: {}",
                context.getName()
        );

        extent.flush();

        ExtentTestManager.removeTest();
    }

    private String captureScreenshot(
            ITestResult result) {

        WebDriver driver;

        try {

            driver =
                    DriverFactory.getDriver();

        } catch (Exception e) {

            logger.error(
                    "Unable to get WebDriver",
                    e
            );

            return null;
        }

        if (driver == null) {

            logger.error(
                    "WebDriver is null"
            );

            return null;
        }

        String timestamp =
                new SimpleDateFormat(
                        "yyyyMMdd_HHmmss"
                ).format(new Date());

        String testName =
                result.getMethod()
                       .getMethodName();

        String fileName =
                testName
                + "_"
                + timestamp
                + ".png";

        Path directory =
                Paths.get("screenshots");

        try {

            Files.createDirectories(
                    directory
            );

            File screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            Path destination =
                    directory.resolve(fileName);

            Files.copy(
                    screenshot.toPath(),
                    destination
            );

            logger.info(
                    "Screenshot saved: {}",
                    destination.toAbsolutePath()
            );

            return destination
                    .toAbsolutePath()
                    .toString();

        } catch (IOException e) {

            logger.error(
                    "Unable to save screenshot",
                    e
            );

            return null;
        }
    }
}