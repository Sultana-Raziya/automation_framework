package inventory;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

public class InventoryTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void verifyInventoryPage() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Incorrect inventory page title");

		Assert.assertEquals(inventoryPage.getProductCount(), 6, "Incorrect number of products");
	}

	@Test(groups = { "regression" })
	public void addProductToCart() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		inventoryPage.addProductToCart("Sauce Labs Backpack");

		Assert.assertTrue(inventoryPage.isProductAddedToCart("Sauce Labs Backpack"),
				"Sauce Labs Backpack was not added to cart");

		Assert.assertEquals(inventoryPage.getCartItemCount(), "1", "Cart count is incorrect");
	}

	@Test(groups = { "regression" })
	public void verifyProductDisplayed() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		Assert.assertTrue(inventoryPage.isProductDisplayed("Sauce Labs Backpack"),
				"Sauce Labs Backpack was not displayed");
	}

	@Test(groups = { "regression" })
	public void verifyAddAndRemoveProduct() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		inventoryPage.addProductToCart("Sauce Labs Backpack");

		Assert.assertEquals(inventoryPage.getCartItemCount(), "1", "Cart badge should contain 1 item");

		inventoryPage.removeProductFromCart("Sauce Labs Backpack");

		Assert.assertFalse(inventoryPage.isCartBadgeDisplayed(),
				"Cart badge should not be displayed after removing the product");
	}

	@Test(groups = { "regression" })
	public void verifyProductSorting() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		inventoryPage.sortProducts("Price (low to high)");

		Assert.assertEquals(inventoryPage.getProductPrices().get(0), "$7.99",
				"Products were not sorted by price correctly");
	}

}