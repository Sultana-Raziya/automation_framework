package pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.LoggerUtil;

public class CartPage extends BasePage {

	private final Logger logger = LoggerUtil.getLogger(CartPage.class);

	private final By cartContainer = By.id("cart_contents_container");

	private final By cartItems = By.cssSelector(".cart_item");

	private final By cartItemNames = By.cssSelector(".inventory_item_name");

	private final By cartItemPrices = By.cssSelector(".inventory_item_price");

	private final By continueShoppingButton = By.id("continue-shopping");

	private final By checkoutButton = By.id("checkout");

	public CartPage(WebDriver driver) {

		super(driver);
	}

	public boolean isCartPageDisplayed() {

		logger.info("Checking whether cart page is displayed");

		return isDisplayed(cartContainer);
	}

	public int getCartItemCount() {

		logger.debug("Getting cart item count");

		return driver.findElements(cartItems).size();
	}

	public List<String> getCartProductNames() {

		logger.debug("Getting product names from cart");

		return driver.findElements(cartItemNames).stream().map(WebElement::getText).toList();
	}

	public List<String> getCartProductPrices() {

		logger.debug("Getting product prices from cart");

		return driver.findElements(cartItemPrices).stream().map(WebElement::getText).toList();
	}

	public void removeProduct(String productName) {

		logger.info("Removing product from cart: {}", productName);

		String productId = productName.toLowerCase().replace(" ", "-");

		By removeButton = By.cssSelector("[data-test='remove-" + productId + "']");

		click(removeButton);
	}

	public InventoryPage continueShopping() {

		logger.info("Continuing shopping");

		click(continueShoppingButton);

		return new InventoryPage(driver);
	}

	public CheckoutPage proceedToCheckout() {

		logger.info("Proceeding to checkout");

		click(checkoutButton);

		wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

		return new CheckoutPage(driver);
	}
}
