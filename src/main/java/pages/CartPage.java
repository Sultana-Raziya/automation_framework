package pages;

	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class CartPage extends BasePage {

	    private By cartItems =
	            By.cssSelector(".cart_item");

	    private By cartItemNames =
	            By.cssSelector(".inventory_item_name");

	    private By cartItemPrices =
	            By.cssSelector(".inventory_item_price");

	    private By removeButtons =
	            By.cssSelector(".cart_button");

	    private By checkoutButton =
	            By.id("checkout");

	    private By continueShoppingButton =
	            By.id("continue-shopping");

	    public CartPage(WebDriver driver) {

	        super(driver);
	    }

	    public int getCartItemCount() {

	        return driver.findElements(cartItems).size();
	    }

	    public List<String> getCartItemNames() {

	        List<WebElement> elements =
	                driver.findElements(cartItemNames);

	        return elements.stream()
	                .map(WebElement::getText)
	                .toList();
	    }

	    public List<String> getCartItemPrices() {

	        List<WebElement> elements =
	                driver.findElements(cartItemPrices);

	        return elements.stream()
	                .map(WebElement::getText)
	                .toList();
	    }

	    public void removeProduct(String productName) {

	        By removeButton = By.xpath(
	                "//div[@class='cart_item']" +
	                "[.//div[@class='inventory_item_name'" +
	                " and normalize-space()='" +
	                productName +
	                "']]" +
	                "//button[contains(@id,'remove')]"
	        );

	        click(removeButton);
	    }

	    public void clickCheckout() {

	        click(checkoutButton);
	    }

	    public void continueShopping() {

	        click(continueShoppingButton);
	    }

		public By getRemoveButtons() {
			return removeButtons;
		}

		public void setRemoveButtons(By removeButtons) {
			this.removeButtons = removeButtons;
		}
	}


