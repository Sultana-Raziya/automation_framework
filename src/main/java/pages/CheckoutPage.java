package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.LoggerUtil;

public class CheckoutPage extends BasePage {

	private final Logger logger = LoggerUtil.getLogger(CheckoutPage.class);

	private By firstName = By.id("first-name");

	private By lastName = By.id("last-name");

	private By postalCode = By.id("postal-code");

	private By continueButton = By.id("continue");

	private By cancelButton = By.id("cancel");

	private By checkoutOverview = By.id("checkout_summary_container");

	private By finishButton = By.id("finish");

	private By orderConfirmation = By.cssSelector(".complete-header");

	private By orderConfirmationText = By.cssSelector(".complete-text");

	private By errorMessage = By.cssSelector("[data-test='error']");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public boolean isCheckoutInformationDisplayed() {

		logger.info("Checking whether checkout information page is displayed");

		return isDisplayed(firstName) && isDisplayed(lastName) && isDisplayed(postalCode);
	}

	public void enterFirstName(String value) {
		logger.debug("Enter first name");
		type(firstName, value);
	}

	public void enterLastName(String value) {
		logger.debug("Enter last name");
		type(lastName, value);
	}

	public void enterPostalCode(String value) {
		logger.debug("Entering postal code ");
		type(postalCode, value);
	}

	public void clickContinue() {
		logger.info("Clicking continue button ");
		click(continueButton);
	}

	public void clickCancel() {
		logger.info("Clicking cancel button ");
		click(cancelButton);
	}

	public void enterCustomerInformation(String firstNameValue, String lastNameValue, String postalCodeValue) {

		logger.debug("Entering customer info ");
		enterFirstName(firstNameValue);
		enterLastName(lastNameValue);
		enterPostalCode(postalCodeValue);

		logger.debug("Customer info entered successfull");
	}

	public void continueToOverview() {

		logger.info("Clicking continue button");

		clickContinue();

	}

	public boolean isCheckoutOverviewDisplayed() {
		logger.debug("Checking checkout overview visibility ");
		try {
			waitForElementVisible(checkoutOverview);
			return true;
		} catch (Exception e) {
			logger.error("Checkout overview was not displayed", e);
			return false;
		}
	}

	public void clickFinish() {
		logger.info("Clicking Finish button ");
		click(finishButton);
	}

	public boolean isOrderConfirmationDisplayed() {
		logger.info("Checking order confirmation visibility");
		return isDisplayed(orderConfirmation);
	}

	public String getOrderConfirmationMessage() {
		logger.info("Getting order confirmation message ");
		return getText(orderConfirmation);
	}

	public String getOrderConfirmationDetails() {
		logger.debug("Getting order confirmation details ");
		return getText(orderConfirmationText);
	}

	public String getErrorMessage() {

		logger.debug("Getting checkout error message");

		if (isDisplayed(errorMessage)) {
			return getText(errorMessage);
		}

		return "";
	}

	public boolean isErrorMessageDisplayed() {
		logger.debug("Checking checkout error visibility");

		return isDisplayed(errorMessage);
	}

}
