package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;
import utils.LoggerUtil;

public class MenuPage {

	private final Logger logger = LoggerUtil.getLogger(MenuPage.class);

	private final ElementUtils element;

	private final By menuButton = By.id("react-burger-menu-btn");

	private final By logoutLink = By.id("logout_sidebar_link");

	private final By menuContainer = By.cssSelector(".bm-menu-wrap");

	private final By loginButton = By.id("login-button");

	public MenuPage(WebDriver driver) {

		this.element = new ElementUtils(driver, logger);
	}

	public void openMenu() {

		logger.info("Opening menu");

		element.click(menuButton);
	}

	public boolean isMenuDisplayed() {

		logger.info("Checking whether menu is displayed");

		return element.isDisplayed(menuContainer);
	}

	public LoginPage logout() {

		logger.info("Logging out");

		WebDriver driver = element.getDriver();

		By logoutLink = By.id("logout_sidebar_link");

		WebDriverWait logoutWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement logoutElement = logoutWait.until(ExpectedConditions.elementToBeClickable(logoutLink));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutElement);

		logoutWait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));

		logger.info("Logout successful");

		return new LoginPage(driver);
	}

	public boolean isLoginPageDisplayed() {

		logger.info("Checking whether login page is displayed");

		return element.isDisplayed(loginButton);
	}

	public By getLogoutLink() {
		return logoutLink;
	}
}
