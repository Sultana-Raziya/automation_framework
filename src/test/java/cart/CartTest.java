package cart;

	import org.testng.Assert;
	import org.testng.annotations.Test;

	import  base.BaseTest;
	import  pages.CartPage;
	import  pages.InventoryPage;
	import  pages.LoginPage;

	public class CartTest extends BaseTest {

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
	    public void verifyProductAddedToCart() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        Assert.assertEquals(
	                inventoryPage.getCartItemCount(),
	                "1",
	                "Cart badge should display 1"
	        );

	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        Assert.assertEquals(
	                cartPage.getCartItemCount(),
	                1,
	                "Cart should contain one product"
	        );

	        Assert.assertTrue(
	                cartPage.getCartItemNames()
	                        .contains("Sauce Labs Backpack"),
	                "Expected product was not found in cart"
	        );
	    }
	    @Test(groups = {"regression"})
	    public void removeProductFromCart() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        Assert.assertEquals(
	                cartPage.getCartItemCount(),
	                1
	        );

	        cartPage.removeProduct(
	                "Sauce Labs Backpack"
	        );

	        Assert.assertEquals(
	                cartPage.getCartItemCount(),
	                0,
	                "Product was not removed from cart"
	        );
	    }
	    
	    @Test(groups = {"regression"})
	    public void addMultipleProductsToCart() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        inventoryPage.addProductToCart(
	                "Sauce Labs Bike Light"
	        );

	        Assert.assertEquals(
	                inventoryPage.getCartItemCount(),
	                "2",
	                "Cart badge should display 2"
	        );

	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        Assert.assertEquals(
	                cartPage.getCartItemCount(),
	                2,
	                "Cart should contain two products"
	        );

	        Assert.assertTrue(
	                cartPage.getCartItemNames()
	                        .contains("Sauce Labs Backpack")
	        );

	        Assert.assertTrue(
	                cartPage.getCartItemNames()
	                        .contains("Sauce Labs Bike Light")
	        );
	    }
	    
	    @Test(groups = {"smoke","regression"})
	    public void navigateToCheckout() {

	        InventoryPage inventoryPage =
	                loginToInventory();

	        inventoryPage.addProductToCart(
	                "Sauce Labs Backpack"
	        );

	        inventoryPage.openCart();

	        CartPage cartPage =
	                new CartPage(driver);

	        cartPage.clickCheckout();

	        Assert.assertTrue(
	                driver.getCurrentUrl()
	                        .contains("checkout-step-one.html"),
	                "Checkout page was not opened"
	        );
	    }

	}


