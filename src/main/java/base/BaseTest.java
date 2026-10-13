package base;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import config.ConfigReader;
import drivers.DriverFactory;
import utils.LoggerUtil;
import utils.WaitUtils;
import listeners.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

	protected WebDriver driver;
	protected WaitUtils wait;

	protected final Logger logger = LoggerUtil.getLogger(getClass());

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		logger.info("========== TEST SETUP STARTED ==========");

		String browser = ConfigReader.getRequiredProperty("browser");

		boolean headless = Boolean.parseBoolean(ConfigReader.getRequiredProperty("headless"));

		String baseUrl = ConfigReader.getRequiredProperty("baseUrl");

		logger.info("Browser: {}", browser);
		logger.info("Headless: {}", headless);
		logger.info("Base URL: {}", baseUrl);

		DriverFactory.initializeDriver(browser, headless);

		driver = DriverFactory.getDriver();

		// create WaitUtils after driver is initilized
		wait = new WaitUtils(driver);
		wait.waitForPageLoad();

		// add a page-load timeout
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

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
