package checkout;

import org.testng.Assert;
	import org.testng.annotations.Test;

	import  base.BaseTest;
	import  pages.CartPage;
	import  pages.CheckoutPage;
	import  pages.InventoryPage;
	import  pages.LoginPage;

	public class CheckoutTest extends BaseTest {

		@Test(groups = {"smoke","regression"})
	    public void successfulCheckoutTest() {

	        // Login
	        LoginPage loginPage =
	                new LoginPage(driver);

	        loginPage.loginAs(
	                "standard_user",
	                "secret_sauce"
	        );

	        // Inventory
	        InventoryPage inventoryPage =
	                new InventoryPage(driver);

	        Assert.assertTrue(
	                inventoryPage.isInventoryPageDisplayed(),
	                "Inventory page was not displayed"
	        );

	        // Add product
	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        Assert.assertEquals(
	                inventoryPage.getCartItemCount(),
	                "1",
	                "Cart should contain one product"
	        );

	        // Open cart
	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        Assert.assertEquals(
	                cartPage.getCartItemCount(),
	                1,
	                "Cart should contain one item"
	        );

	        Assert.assertTrue(
	                cartPage.getCartItemNames()
	                        .contains("Sauce Labs Backpack"),
	                "Backpack was not found in cart"
	        );

	        // Checkout
	        cartPage.clickCheckout();

	        CheckoutPage checkoutPage =
	                new CheckoutPage(driver);

	        // Customer information
	        checkoutPage.enterCustomerInformation(
	                "John",
	                "Tester",
	                "500001"
	        );

	        checkoutPage.continueToOverview();

	        // Verify overview
	        Assert.assertTrue(
	                checkoutPage.isCheckoutOverviewDisplayed(),
	                "Checkout overview was not displayed"
	        );

	        // Finish order
	        checkoutPage.clickFinish();

	        // Verify order
	        Assert.assertTrue(
	                checkoutPage.isOrderConfirmationDisplayed(),
	                "Order confirmation was not displayed"
	        );

	        Assert.assertEquals(
	                checkoutPage.getOrderConfirmationMessage(),
	                "Thank you for your order!",
	                "Incorrect order confirmation message"
	        );
	    }
	    
		@Test(groups = {"regression"})
	    public void checkoutWithoutCustomerInformation() {

	        LoginPage loginPage =
	                new LoginPage(driver);

	        loginPage.loginAs(
	                "standard_user",
	                "secret_sauce"
	        );

	        InventoryPage inventoryPage =
	                new InventoryPage(driver);

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        cartPage.clickCheckout();

	        CheckoutPage checkoutPage =
	                new CheckoutPage(driver);

	        checkoutPage.clickContinue();

	        String error =
	                checkoutPage.getErrorMessage();

	        Assert.assertTrue(
	                error.contains("First Name"),
	                "Expected first-name validation was not displayed"
	        );
	    }

	}


