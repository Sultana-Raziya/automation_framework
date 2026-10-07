package base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import config.ConfigReader;
import drivers.DriverFactory;
import utils.LoggerUtil;
import listeners.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    protected final Logger logger =
            LoggerUtil.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        logger.info("========== TEST SETUP STARTED ==========");

        String browser =
                ConfigReader.getProperty("browser");

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("headless")
                );

        String baseUrl =
                ConfigReader.getProperty("baseUrl");

        logger.info("Browser: {}", browser);
        logger.info("Headless: {}", headless);
        logger.info("Base URL: {}", baseUrl);

        DriverFactory.initializeDriver(
                browser,
                headless
        );

        driver = DriverFactory.getDriver();

        logger.info("WebDriver initialized successfully");

        driver.get(baseUrl);

        logger.info("Navigated to application");

        logger.info("========== TEST SETUP COMPLETED ==========");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        logger.info("========== TEST CLEANUP STARTED ==========");

        DriverFactory.quitDriver();

        driver = null;

        logger.info("WebDriver closed");

        logger.info("========== TEST CLEANUP COMPLETED ==========");
    }
}
