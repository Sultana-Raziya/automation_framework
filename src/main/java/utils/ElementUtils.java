package utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
	private final WebDriver driver;
	private final WaitUtils wait;
	private final Logger logger;

	public ElementUtils(WebDriver driver, Logger logger) {

		this.driver = driver;
		this.logger = logger;
		this.wait = new WaitUtils(driver);
	}

	public void click(By locator) {

		logger.debug("Clicking element: {}", locator);

		wait.waitForClickable(locator).click();
	}

	public void type(By locator, String text) {

		logger.debug("Typing into element: {}", locator);

		WebElement element = wait.waitForVisible(locator);

		element.clear();

		element.sendKeys(text);
	}

	public String getText(By locator) {

		logger.debug("Getting text from element: {}", locator);

		return wait.waitForVisible(locator).getText();
	}

	public boolean isDisplayed(By locator) {

		try {

			return wait.waitForVisible(locator).isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
