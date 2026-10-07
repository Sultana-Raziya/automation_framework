package inventory;

	import org.testng.Assert;
	import org.testng.annotations.Test;

	import base.BaseTest;
	import pages.InventoryPage;
	import pages.LoginPage;

	public class InventoryTest extends BaseTest {

	    private InventoryPage loginToInventory() {

	        LoginPage loginPage =
	                new LoginPage(driver);

	        loginPage.loginAs(
	                "standard_user",
	                "secret_sauce"
	        );

	        return new InventoryPage(driver);
	    }

	    @Test(groups = {"smoke","regression"})
	    public void verifyProductsDisplayed() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        Assert.assertTrue(
	                inventoryPage.isInventoryPageDisplayed(),
	                "Inventory page was not displayed"
	        );

	        Assert.assertTrue(
	                inventoryPage.getProductCount() > 0,
	                "No products are displayed"
	        );
	    }

	    @Test(groups = {"regression"})
	    public void verifyProductNamesDisplayed() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        Assert.assertTrue(
	                inventoryPage.getProductNames().size() > 0,
	                "Product names were not displayed"
	        );
	    }

	    @Test(groups = {"smoke", "regression"})
	    public void addProductToCart() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        inventoryPage.openCart();

	        Assert.assertTrue(
	                driver.getCurrentUrl()
	                        .contains("cart.html"),
	                "Cart page was not opened"
	        );
	        
	        Assert.assertEquals(
	        	    inventoryPage.getCartItemCount(),"1");

	    }
	    
	    @Test(groups = {"regression"})
	    public void sortProductsByPriceLowToHigh() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.sortProducts(
	                "Price (low to high)"
	        );

	        Assert.assertEquals(
	                inventoryPage.getProductPrices().get(0),
	                "$7.99",
	                "Products were not sorted by price correctly"
	        );
	    }
	    
	    @Test(groups = {"regression"})
	    public void sortProductsByPriceHighToLow() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.sortProducts(
	                "Price (high to low)"
	        );

	        Assert.assertEquals(
	                inventoryPage.getProductPrices().get(0),
	                "$49.99",
	                "Products were not sorted high to low"
	        );
	    }


	}


