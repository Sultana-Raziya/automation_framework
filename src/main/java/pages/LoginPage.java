package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;
import utils.LoggerUtil;

public class LoginPage {

	private final WebDriver driver;

	private final Logger logger = LoggerUtil.getLogger(LoginPage.class);

	private final ElementUtils element;

	private final By usernameField = By.id("user-name");

	private final By passwordField = By.id("password");

	private final By loginButton = By.id("login-button");

	private final By errorMessage = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		this.element = new ElementUtils(driver, logger);
	}

	public void loginAs(String username, String password) {

		logger.info("Attempting login with username: {}", username);

		element.type(usernameField, username);

		element.type(passwordField, password);

		element.click(loginButton);
	}

	public String getErrorMessage() {

		return element.getText(errorMessage);
	}

	public WebDriver getDriver() {
		return driver;
	}
}
