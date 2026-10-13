package menu;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import pages.MenuPage;
import utils.TestData;

public class MenuTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void verifyMenuDisplayed() {

		logger.info("Starting menu display test");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		MenuPage menuPage = new MenuPage(driver);

		menuPage.openMenu();

		Assert.assertTrue(menuPage.isMenuDisplayed(), "Menu was not displayed");

		logger.info("Menu successfully verified");
	}

	@Test(groups = { "smoke", "regression" })
	public void verifyLogout() {

		logger.info("Starting logout test");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(TestData.getStandardUsername(), TestData.getStandardPassword());

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		MenuPage menuPage = new MenuPage(driver);

		menuPage.openMenu();

		Assert.assertTrue(menuPage.isMenuDisplayed(), "Menu was not displayed");

		menuPage.logout();

		Assert.assertTrue(menuPage.isLoginPageDisplayed(), "Login page was not displayed after logout");

		logger.info("Logout successfully verified");
	}

}
