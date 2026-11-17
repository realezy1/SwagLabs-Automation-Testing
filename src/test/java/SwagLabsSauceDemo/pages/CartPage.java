package SwagLabsSauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    private final By cartTitle = By.cssSelector("[data-test='title']");
    private final By cartItemNames = By.className("inventory_item_name");
    private final By cartItemDescriptions = By.className("inventory_item_desc");
    private final By cartItemPrices = By.className("inventory_item_price");
    private final By removeButton = By.cssSelector("[data-test^='remove-']");
    private final By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By cartItems = By.className("cart_item");

    // Methods
    public String getCartTitle() {
        return driver.findElement(cartTitle).getText();
    }

    public String getCartItemName(int index) {
        return driver.findElements(cartItemNames).get(index).getText();
    }

    public String getCartItemDescription(int index) {
        return driver.findElements(cartItemDescriptions).get(index).getText();
    }

    public String getCartItemPrice(int index) {
        return driver.findElements(cartItemPrices).get(index).getText();
    }

    public void removeCartItem() {
        driver.findElement(removeButton).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void removeCartItem(String productName) {
        By removeProductButton = By.cssSelector("[data-test='remove-" + productName + "']");
        driver.findElement(removeProductButton).click();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}
