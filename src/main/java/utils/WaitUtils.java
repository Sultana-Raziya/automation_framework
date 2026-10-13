package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public WaitUtils(WebDriver driver) {

		this.driver = driver;

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public WebElement waitForVisible(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForClickable(By locator) {

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean waitForUrlContains(String value) {

		return wait.until(ExpectedConditions.urlContains(value));
	}

	public boolean waitForTitleContains(String value) {

		return wait.until(ExpectedConditions.titleContains(value));
	}

	public boolean waitForInvisible(By locator) {

		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// generic wait for page readiness
	public void waitForPageLoad() {

		wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public WebDriver getDriver() {
		return driver;
	}
}
