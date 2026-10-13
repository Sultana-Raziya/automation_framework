package checkout;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class CheckoutTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void successfulCheckoutTest() {

		// Login
		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		// Inventory
		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		// Add product
		inventoryPage.addProductToCart("Sauce Labs Backpack");

		Assert.assertEquals(inventoryPage.getCartItemCount(), "1", "Cart should contain one product");

		// Open cart
		CartPage cartPage = inventoryPage.openCart();
		Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain one item");

		Assert.assertTrue(cartPage.getCartProductNames().contains("Sauce Labs Backpack"),
				"Backpack was not found in cart");

		// Checkout
		cartPage.proceedToCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);

		Assert.assertTrue(checkoutPage.isCheckoutInformationDisplayed(), "Checkout information page was not displayed");

		// Customer information
		checkoutPage.enterCustomerInformation("John", "Tester", "500001");

		checkoutPage.continueToOverview();

		// Verify overview
		Assert.assertTrue(checkoutPage.isCheckoutOverviewDisplayed(), "Checkout overview was not displayed");

		// Finish order
		checkoutPage.clickFinish();

		// Verify order
		Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed(), "Order confirmation was not displayed");

		Assert.assertEquals(checkoutPage.getOrderConfirmationMessage(), "Thank you for your order!",
				"Incorrect order confirmation message");
	}

	@Test(groups = { "regression" })
	public void checkoutWithoutCustomerInformation() {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		inventoryPage.addProductToCart("Sauce Labs Backpack");

		inventoryPage.openCart();

		CartPage cartPage = new CartPage(driver);

		cartPage.proceedToCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);

		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorMessageDisplayed(),
				"Expected first-name validation error was not displayed");

		String error = checkoutPage.getErrorMessage();

		Assert.assertTrue(error.contains("First Name"), "Expected first-name validation was not displayed");
	}

	@Test(groups = { "regression" })
	public void cancelCheckoutTest() {

		logger.info("Starting cancel checkout test");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		inventoryPage.openCart();

		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = cartPage.proceedToCheckout();

		checkoutPage.clickCancel();

		// Your existing CartPage method verifies the cart
		Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page was not displayed after canceling checkout");

		Assert.assertTrue(cartPage.getCartProductNames().contains("Sauce Labs Backpack"),
				"Product was not found in cart after cancellation");

		logger.info("Checkout canceled successfully");
	}

}
