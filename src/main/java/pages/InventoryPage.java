package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage {

    private By inventoryContainer =
            By.id("inventory_container");

    private By pageTitle =
            By.cssSelector(".title");

    private By productItems =
            By.cssSelector(".inventory_item");

    private By productNames =
            By.cssSelector(".inventory_item_name");

    private By productPrices =
            By.cssSelector(".inventory_item_price");

    private By sortDropdown =
            By.cssSelector(".product_sort_container");

    private By shoppingCart =
            By.cssSelector(".shopping_cart_link");

    private By cartBadge =
            By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInventoryPageDisplayed() {
        return isDisplayed(inventoryContainer);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public List<String> getProductNames() {

        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getProductPrices() {

        return driver.findElements(productPrices)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void sortProducts(String option) {

        selectByVisibleText(
                sortDropdown,
                option
        );
    }

    public void addProductToCart(String productName) {

        String productId =
                getProductId(productName);

        By addButton = By.cssSelector(
                "[data-test='add-to-cart-" +
                productId +
                "']"
        );

        click(addButton);
    }

    private String getProductId(String productName) {

        return productName
                .toLowerCase()
                .replace(" ", "-");
    }

    public String getCartItemCount() {

        return getText(cartBadge);
    }

    public void openCart() {

        click(shoppingCart);
    }
}
