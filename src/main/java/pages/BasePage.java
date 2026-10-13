package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {

		this.driver = driver;

		int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitWait"));

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
	}

	protected WebElement waitForElementVisible(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected Boolean waitForLocatedElementInvisible(By locator) {

		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected WebElement waitForElementClickable(By locator) {

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected void click(By locator) {

		waitForElementClickable(locator).click();
	}

	protected void type(By locator, String text) {

		WebElement element = waitForElementVisible(locator);

		element.clear();
		element.sendKeys(text);
	}

	protected String getText(By locator) {

		return waitForElementVisible(locator).getText();
	}

	protected boolean isDisplayed(By locator) {

		try {

			return waitForElementVisible(locator).isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	protected void selectByVisibleText(By locator, String text) {

		WebElement dropdown = waitForElementVisible(locator);

		Select select = new Select(dropdown);

		select.selectByVisibleText(text);
	}

	protected void handleAlertIfPresent() {

		try {
			wait.until(ExpectedConditions.alertIsPresent());

			driver.switchTo().alert().accept();

		} catch (Exception e) {
			// No alert present
		}
	}

}
