package SwagLabsSauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators - Checkout Information
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    // Locators - Checkout Overview
    private final By checkoutTitle = By.cssSelector("[data-test='title']");
    private final By checkoutItemNames = By.className("inventory_item_name");
    private final By checkoutItemDescriptions = By.className("inventory_item_desc");
    private final By checkoutItemPrices = By.className("inventory_item_price");

    private final By paymentInfoLabel = By.cssSelector("[data-test='payment-info-label']");
    private final By paymentInfoValue = By.cssSelector("[data-test='payment-info-value']");

    private final By shippingInfoLabel = By.cssSelector("[data-test='shipping-info-label']");
    private final By shippingInfoValue = By.cssSelector("[data-test='shipping-info-value']");

    private final By subtotalLabel = By.cssSelector("[data-test='subtotal-label']");
    private final By taxLabel = By.cssSelector("[data-test='tax-label']");
    private final By totalLabel = By.cssSelector("[data-test='total-label']");

    // Locators - Checkout Complete
    private final By finishButton = By.id("finish");
    private final By ponyExpressImage = By.cssSelector("[data-test='pony-express']");
    private final By completeHeader = By.className("complete-header");
    private final By completeText = By.className("complete-text");
    private final By backToProductsButton = By.id("back-to-products");
    private final By generatePdfOrderButton = By.id("generate-pdf-order");

    // Methods - Checkout Information
    public void enterFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    public void enterLastName(String name) {
        driver.findElement(lastName).sendKeys(name);
    }

    public void enterPostalCode(String code) {
        driver.findElement(postalCode).sendKeys(code);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    // Methods - Checkout Overview
    public String getCheckoutTitle() {
        return driver.findElement(checkoutTitle).getText();
    }

    public String getCheckoutItemName(int index) {
        return driver.findElements(checkoutItemNames).get(index).getText();
    }

    public String getCheckoutItemDescription(int index) {
        return driver.findElements(checkoutItemDescriptions).get(index).getText();
    }

    public String getCheckoutItemPrice(int index) {
        return driver.findElements(checkoutItemPrices).get(index).getText();
    }

    public boolean isPaymentInfoLabelDisplayed() {
        return driver.findElement(paymentInfoLabel).isDisplayed();
    }

    public boolean isPaymentInfoValueDisplayed() {
        return driver.findElement(paymentInfoValue).isDisplayed();
    }

    public boolean isShippingInfoLabelDisplayed() {
        return driver.findElement(shippingInfoLabel).isDisplayed();
    }

    public boolean isShippingInfoValueDisplayed() {
        return driver.findElement(shippingInfoValue).isDisplayed();
    }

    public String getSubtotalLabel() {
        return driver.findElement(subtotalLabel).getText();
    }

    public String getTaxLabel() {
        return driver.findElement(taxLabel).getText();
    }

    public String getTotalLabel() {
        return driver.findElement(totalLabel).getText();
    }

    // Methods - Checkout Complete
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public boolean isPonyExpressDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ponyExpressImage)).isDisplayed();
    }

    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }

    public String getCompleteText() {
        return driver.findElement(completeText).getText();
    }

    public boolean isBackToProductsDisplayed() {
        return driver.findElement(backToProductsButton).isDisplayed();
    }

    public boolean isBackToProductsEnabled() {
        return driver.findElement(backToProductsButton).isEnabled();
    }

    public boolean isGeneratePdfOrderDisplayed() {
        return driver.findElement(generatePdfOrderButton).isDisplayed();
    }

    public boolean isGeneratePdfOrderEnabled() {
        return driver.findElement(generatePdfOrderButton).isEnabled();
    }

    public void clickBackToProducts() {
        driver.findElement(backToProductsButton).click();
    }
}
