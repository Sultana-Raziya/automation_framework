package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.LoggerUtil;

public class LoginPage extends BasePage {

    private By usernameField =
            By.id("user-name");

    private By passwordField =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    private By errorMessage =
            By.cssSelector("[data-test='error']");
    
    private final Logger logger =
            LoggerUtil.getLogger(LoginPage.class);



    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public void enterUsername(String username) {

        type(usernameField, username);
    }

    public void enterPassword(String password) {

        type(passwordField, password);
    }

    public void clickLogin() {

        click(loginButton);
    }

    public void loginAs(String username,String password) {

        logger.info("Attempting login with username: {}",username);
        
        type(usernameField, username);

        logger.debug("Username entered");

        type(passwordField, password);

        logger.debug("Password entered");

        click(loginButton);

        logger.info("Login button clicked");

    }

    public String getErrorMessage() {

        return getText(errorMessage);
    }
}
