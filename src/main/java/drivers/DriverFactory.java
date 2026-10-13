package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverFactory() {
	}

	public static void initializeDriver(String browser, boolean headless) {

		if (browser == null || browser.trim().isEmpty()) {

			throw new IllegalArgumentException("Browser cannot be empty");
		}

		switch (browser.toLowerCase()) {

		case "chrome":

			ChromeOptions chromeOptions = new ChromeOptions();

			configureHeadless(chromeOptions, headless);

			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-popup-blocking");

			driver.set(new ChromeDriver(chromeOptions));

			break;

		case "edge":

			EdgeOptions edgeOptions = new EdgeOptions();

			configureHeadless(edgeOptions, headless);

			edgeOptions.addArguments("--start-maximized");
			edgeOptions.addArguments("--disable-notifications");
			edgeOptions.addArguments("--disable-popup-blocking");

			driver.set(new EdgeDriver(edgeOptions));

			break;

		default:

			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}

	private static void configureHeadless(ChromeOptions options, boolean headless) {

		if (headless) {
			options.addArguments("--headless=new");
		}
	}

	private static void configureHeadless(EdgeOptions options, boolean headless) {

		if (headless) {
			options.addArguments("--headless=new");
		}
	}

	public static WebDriver getDriver() {

		WebDriver webDriver = driver.get();

		if (webDriver == null) {

			throw new IllegalStateException("WebDriver is null. " + "Driver was not initialized.");
		}

		return webDriver;
	}

	public static void quitDriver() {

		WebDriver webDriver = driver.get();

		if (webDriver != null) {

			webDriver.quit();

			driver.remove();
		}
	}
}