package cart;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class CartTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void addProductToCartTest() {

		logger.info("Starting add product to cart test");

		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		inventoryPage.addProductToCart("Sauce Labs Backpack");

		Assert.assertEquals(inventoryPage.getCartItemCount(), "1", "Cart should contain one product");

		CartPage cartPage = inventoryPage.openCart();

		Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page was not displayed");

		Assert.assertTrue(cartPage.getCartProductNames().contains("Sauce Labs Backpack"),
				"Sauce Labs Backpack was not found in cart");

		logger.info("Product successfully verified in cart");
	}

	@Test(groups = { "regression" })
	public void removeProductFromCartTest() {

		logger.info("Starting remove product from cart test");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		// Add product
		inventoryPage.addProductToCart("Sauce Labs Backpack");

		// Verify product was actually added
		Assert.assertTrue(inventoryPage.isProductAddedToCart("Sauce Labs Backpack"),
				"Sauce Labs Backpack was not added to cart");

		// Verify cart badge
		Assert.assertEquals(inventoryPage.getCartItemCount(), "1", "Cart badge should show one product");

		// Open cart
		CartPage cartPage = inventoryPage.openCart();

		Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page was not displayed");

		// Verify product exists before removing
		Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain one product before removal");

		// Remove product
		cartPage.removeProduct("Sauce Labs Backpack");

		// Verify cart is empty
		Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after removing product");

		logger.info("Product successfully removed from cart");
	}

	@Test(groups = { "regression" })
	public void continueShoppingTest() {

		logger.info("Starting continue shopping test");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		inventoryPage.addProductToCart("Sauce Labs Backpack");

		CartPage cartPage = inventoryPage.openCart();

		InventoryPage returnedInventoryPage = cartPage.continueShopping();

		Assert.assertTrue(returnedInventoryPage.isInventoryPageDisplayed(),
				"Inventory page was not displayed after continuing shopping");

		logger.info("Continue shopping successfully returned to inventory");
	}
	
	@Test(groups = { "smoke", "regression" })
	public void addMultipleProductsToCartTest() {

	    logger.info("Starting add multiple products to cart test");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.loginAs(
	            TestData.getStandardUsername(),
	            TestData.getStandardPassword()
	    );

	    InventoryPage inventoryPage = new InventoryPage(driver);

	    Assert.assertTrue(
	            inventoryPage.isInventoryPageDisplayed(),
	            "Inventory page was not displayed"
	    );

	    // Add multiple products
	    inventoryPage.addProductToCart("Sauce Labs Backpack");
	    inventoryPage.addProductToCart("Sauce Labs Bike Light");
	    inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

	    // Verify cart badge count
	    Assert.assertEquals(
	            inventoryPage.getCartItemCount(),
	            "3",
	            "Cart badge should show three products"
	    );

	    logger.info("Multiple products successfully added to cart");
	}
	
	@Test(groups = { "regression" })
	public void verifyCartContentsTest() {

	    logger.info("Starting verify cart contents test");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.loginAs(
	            TestData.getStandardUsername(),
	            TestData.getStandardPassword()
	    );

	    InventoryPage inventoryPage = new InventoryPage(driver);

	    Assert.assertTrue(
	            inventoryPage.isInventoryPageDisplayed(),
	            "Inventory page was not displayed"
	    );

	    // Add products to cart
	    inventoryPage.addProductToCart("Sauce Labs Backpack");
	    inventoryPage.addProductToCart("Sauce Labs Bike Light");
	    inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

	    // Open cart
	    CartPage cartPage = inventoryPage.openCart();

	    Assert.assertTrue(
	            cartPage.isCartPageDisplayed(),
	            "Cart page was not displayed"
	    );

	    // Verify total number of products
	    Assert.assertEquals(
	            cartPage.getCartItemCount(),
	            3,
	            "Cart should contain three products"
	    );

	    // Verify product names
	    List<String> expectedProducts = List.of(
	            "Sauce Labs Backpack",
	            "Sauce Labs Bike Light",
	            "Sauce Labs Bolt T-Shirt"
	    );

	    List<String> actualProducts = cartPage.getCartProductNames();

	    Assert.assertTrue(
	            actualProducts.containsAll(expectedProducts),
	            "Cart does not contain all expected products. Actual products: "
	                    + actualProducts
	    );

	    logger.info("All products successfully verified in cart");
	}
}
