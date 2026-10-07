package pages;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class CheckoutPage extends BasePage {

	    private By firstName =
	            By.id("first-name");

	    private By lastName =
	            By.id("last-name");

	    private By postalCode =
	            By.id("postal-code");

	    private By continueButton =
	            By.id("continue");

	    private By cancelButton =
	            By.id("cancel");

	    private By checkoutOverview =
	            By.id("checkout_summary_container");

	    private By finishButton =
	            By.id("finish");

	    private By orderConfirmation =
	            By.cssSelector(".complete-header");

	    private By orderConfirmationText =
	            By.cssSelector(".complete-text");

	    private By errorMessage =
	            By.cssSelector("[data-test='error']");

	    public CheckoutPage(WebDriver driver) {
	        super(driver);
	    }

	    public void enterFirstName(String value) {
	        type(firstName, value);
	    }

	    public void enterLastName(String value) {
	        type(lastName, value);
	    }

	    public void enterPostalCode(String value) {
	        type(postalCode, value);
	    }

	    public void clickContinue() {
	        click(continueButton);
	    }

	    public void clickCancel() {
	        click(cancelButton);
	    }

	    public void enterCustomerInformation(
	            String firstNameValue,
	            String lastNameValue,
	            String postalCodeValue) {

	        enterFirstName(firstNameValue);
	        enterLastName(lastNameValue);
	        enterPostalCode(postalCodeValue);
	    }

	    public void continueToOverview() {
	        clickContinue();
	    }

	    public boolean isCheckoutOverviewDisplayed() {
	        return isDisplayed(checkoutOverview);
	    }

	    public void clickFinish() {
	        click(finishButton);
	    }

	    public boolean isOrderConfirmationDisplayed() {
	        return isDisplayed(orderConfirmation);
	    }

	    public String getOrderConfirmationMessage() {
	        return getText(orderConfirmation);
	    }

	    public String getOrderConfirmationDetails() {
	        return getText(orderConfirmationText);
	    }

	    public String getErrorMessage() {
	        return getText(errorMessage);
	    }
	}


