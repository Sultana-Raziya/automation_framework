package pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.LoggerUtil;

public class InventoryPage extends BasePage {

	private final Logger logger = LoggerUtil.getLogger(InventoryPage.class);

	private final By inventoryContainer = By.id("inventory_container");

	private final By pageTitle = By.cssSelector(".title");

	private final By productItems = By.cssSelector(".inventory_item");

	private final By productNames = By.cssSelector(".inventory_item_name");

	private final By productPrices = By.cssSelector(".inventory_item_price");

	private final By sortDropdown = By.cssSelector(".product_sort_container");

	private final By shoppingCart = By.cssSelector(".shopping_cart_link");

	private final By cartBadge = By.cssSelector(".shopping_cart_badge");

	public InventoryPage(WebDriver driver) {

		super(driver);
	}

	public boolean isInventoryPageDisplayed() {

		logger.info("Checking whether inventory page is displayed");

		return isDisplayed(inventoryContainer);
	}

	public String getPageTitle() {

		logger.debug("Getting inventory page title");

		return getText(pageTitle);
	}

	public int getProductCount() {

		logger.debug("Getting product count");

		return driver.findElements(productItems).size();
	}

	public List<String> getProductNames() {

		logger.debug("Getting product names");

		return driver.findElements(productNames).stream().map(WebElement::getText).toList();
	}

	public List<String> getProductPrices() {

		logger.debug("Getting product prices");

		return driver.findElements(productPrices).stream().map(WebElement::getText).toList();
	}

	public void sortProducts(String option) {

		logger.info("Sorting products using option: {}", option);

		selectByVisibleText(sortDropdown, option);
	}

	public void addProductToCart(String productName) {

		logger.info("Adding product to cart: {}", productName);

		String productId = getProductId(productName);

		By addButton = By.cssSelector("[data-test='add-to-cart-" + productId + "']");

		click(addButton);
		By removeButton = By.cssSelector("[data-test='remove-" + productId + "']");

		waitForElementVisible(removeButton);

		logger.info("Product successfully added to cart: {}", productName);

	}

	public boolean isProductDisplayed(String productName) {

		return getProductNames().contains(productName);
	}

	private String getProductId(String productName) {

		return productName.toLowerCase().replace(" ", "-");
	}

	public void removeProductFromCart(String productName) {

		logger.info("Removing product from cart: {}", productName);

		String productId = getProductId(productName);

		By removeButton = By.cssSelector("[data-test='remove-" + productId + "']");

		click(removeButton);
	}

	public String getCartItemCount() {

		logger.debug("Getting cart item count");

		return getText(cartBadge);
	}

	public boolean isCartBadgeDisplayed() {

		return isDisplayed(cartBadge);
	}

	public CartPage openCart() {

		logger.info("Opening shopping cart");

		click(shoppingCart);

		return new CartPage(driver);
	}

	public boolean isProductAddedToCart(String productName) {

		logger.info("Checking whether product was added to cart: {}", productName);

		String productId = productName.toLowerCase().replace(" ", "-");

		By removeButton = By.cssSelector("[data-test='remove-" + productId + "']");

		return isDisplayed(removeButton);
	}

}
