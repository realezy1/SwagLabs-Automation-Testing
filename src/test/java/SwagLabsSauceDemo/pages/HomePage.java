
package SwagLabsSauceDemo.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    private final By appLogo = By.className("app_logo");
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By productSort = By.cssSelector("[data-test='product-sort-container']");
    private final By shoppingCart = By.cssSelector("[data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By dynamicCatalogLink = By.id("dynamic_catalog_sidebar_link");
    private final By lazyLoadLink = By.id("dynamic_catalog_lazy_load_link");
    private final By spinnerLink = By.id("dynamic_catalog_spinner_link");
    private final By sliderLink = By.id("dynamic_catalog_slider_link");
    private final By sidebarMenu = By.className("bm-menu");
    private final By logoutButton = By.id("logout_sidebar_link");

    // Methods
    public String getAppLogoText() {
        return driver.findElement(appLogo).getText();
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isProductSortDisplayed() {
        return driver.findElement(productSort).isDisplayed();
    }

    public boolean isProductSortEnabled() {
        return driver.findElement(productSort).isEnabled();
    }

    public int getProductSortOptionsCount() {
        Select sort = new Select(driver.findElement(productSort));
        return sort.getOptions().size();
    }

    public String getSelectedProductSortText() {
        Select sort = new Select(driver.findElement(productSort));
        return sort.getFirstSelectedOption().getText();
    }

    public void selectProductSort(String value) {
        Select sort = new Select(driver.findElement(productSort));
        sort.selectByValue(value);
    }

    public void addProductToCart(String productName) {
        By addToCartButton = By.cssSelector("[data-test='add-to-cart-" + productName + "']");
        driver.findElement(addToCartButton).click();
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.cssSelector("[data-test='remove-" + productName + "']");
        driver.findElement(removeButton).click();
    }

    public String getShoppingCartBadge() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    public boolean isShoppingCartBadgeDisplayed() {
        return driver.findElement(shoppingCartBadge).isDisplayed();
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(By.className("inventory_item_name"));
    }

    public String getProductName(String productId) {
        return driver.findElement(By.id(productId + "_title_link")).getText();
    }

    public boolean isProductImageDisplayed(String productId) {
        return driver.findElement(By.id(productId + "_img_link")).isDisplayed();
    }

    public String getProductDescription(int index) {
        return driver.findElements(By.className("inventory_item_desc")).get(index).getText();
    }

    public String getProductPrice(int index) {
        return driver.findElements(By.className("inventory_item_price")).get(index).getText();
    }

    public void clickShoppingCart() {
        driver.findElement(shoppingCart).click();
    }

    public void openSideBar() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public void clickDynamicCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(dynamicCatalogLink)).click();
    }

    public void clickLazyLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(lazyLoadLink)).click();
    }

    public void clickSpinner() {
        wait.until(ExpectedConditions.elementToBeClickable(spinnerLink)).click();
    }

    public void clickSlider() {
        wait.until(ExpectedConditions.elementToBeClickable(sliderLink)).click();
    }

    public boolean isSidebarDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarMenu)).isDisplayed();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
