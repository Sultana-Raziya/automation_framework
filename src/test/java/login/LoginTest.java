package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import testdata.LoginData;
import testdata.LoginDataProvider;

public class LoginTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void validLoginTest() {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs("standard_user", "secret_sauce");

		InventoryPage inventoryPage = new InventoryPage(driver);

		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = LoginData.class, groups = { "regression" })
	public void invalidLoginTest(String username, String password) {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(username, password);

		String error = loginPage.getErrorMessage();

		Assert.assertTrue(error.length() > 0, "Expected login error was not displayed");
	}

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = { "regression" })
	public void loginTest(String username, String password, String expectedResult) {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginAs(username, password);

		if (expectedResult.equalsIgnoreCase("success")) {

			InventoryPage inventoryPage = new InventoryPage(driver);

			Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed");

		} else {
			Assert.assertTrue(loginPage.getErrorMessage().length() > 0, "Expected login error was not displayed");
		}
	}
}
