package SwagLabsSauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import SwagLabsSauceDemo.pages.LoginPage;
import SwagLabsSauceDemo.pages.HomePage;
import SwagLabsSauceDemo.pages.CartPage;
import SwagLabsSauceDemo.pages.CheckoutPage;

import java.time.Duration;

public class HomePageTest {
    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    private void init(){
        // Initiate browser
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=D:/SeleniumChromeProfile");
        driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");

        //reset browser storage (IMPORTANT)
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();

        //login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //verify successful login
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html")), "Login failed");
        homePage = new HomePage(driver, wait);
        cartPage = new CartPage(driver, wait);
        checkoutPage = new CheckoutPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
    }

    @Test(priority = 0)
    public void checkElement() {
        //check element header app logo
        Assert.assertEquals(homePage.getAppLogoText(), "Swag Labs");

        //check element title
        Assert.assertEquals(homePage.getPageTitle(), "Products");

        //check element product 1
        Assert.assertTrue(homePage.isProductImageDisplayed("item_4"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_4"), "Sauce Labs Backpack");
        homePage.addProductToCart("sauce-labs-backpack");
        homePage.removeProductFromCart("sauce-labs-backpack");

        //check element product 2
        Assert.assertTrue(homePage.isProductImageDisplayed("item_0"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_0"), "Sauce Labs Bike Light");
        homePage.addProductToCart("sauce-labs-bike-light");
        homePage.removeProductFromCart("sauce-labs-bike-light");

        //check element product 3
        Assert.assertTrue(homePage.isProductImageDisplayed("item_1"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_1"), "Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("sauce-labs-bolt-t-shirt");
        homePage.removeProductFromCart("sauce-labs-bolt-t-shirt");

        //check element product 4
        Assert.assertTrue(homePage.isProductImageDisplayed("item_5"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_5"), "Sauce Labs Fleece Jacket");
        homePage.addProductToCart("sauce-labs-fleece-jacket");
        homePage.removeProductFromCart("sauce-labs-fleece-jacket");

        //check element product 5
        Assert.assertTrue(homePage.isProductImageDisplayed("item_2"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_2"), "Sauce Labs Onesie");
        homePage.addProductToCart("sauce-labs-onesie");
        homePage.removeProductFromCart("sauce-labs-onesie");

        //check element product 6
        Assert.assertTrue(homePage.isProductImageDisplayed("item_3"), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(homePage.getProductName("item_3"), "Test.allTheThings() T-Shirt (Red)");
        homePage.addProductToCart("test.allthethings()-t-shirt-(red)");
        homePage.removeProductFromCart("test.allthethings()-t-shirt-(red)");

        //check element all of products item desc
        Assert.assertEquals(homePage.getProductDescription(0),
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(homePage.getProductDescription(1),
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");
        Assert.assertEquals(homePage.getProductDescription(2),
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.");
        Assert.assertEquals(homePage.getProductDescription(3),
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
        Assert.assertEquals(homePage.getProductDescription(4),
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.");
        Assert.assertEquals(homePage.getProductDescription(5),
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.");

        //check element all of products price
        Assert.assertEquals(homePage.getProductPrice(0), "$29.99");
        Assert.assertEquals(homePage.getProductPrice(1), "$9.99");
        Assert.assertEquals(homePage.getProductPrice(2), "$15.99");
        Assert.assertEquals(homePage.getProductPrice(3), "$49.99");
        Assert.assertEquals(homePage.getProductPrice(4), "$7.99");
        Assert.assertEquals(homePage.getProductPrice(5), "$15.99");
    }

    @Test(priority = 1)
    public void openSideBar1() {
        // Open sidebar
        homePage.openSideBar();

        // Open Dynamic Catalog
        homePage.clickDynamicCatalog();

        // Open Lazy Load
        homePage.clickLazyLoad();

        // Verify destination URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-lazy-load.html");
    }

    @Test(priority = 2)
    public void openSideBar2() {
        // Open sidebar
        homePage.openSideBar();

        // Open Dynamic Catalog
        homePage.clickDynamicCatalog();

        // Open Spinner
        homePage.clickSpinner();

        // Verify destination URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-spinner.html");
    }

    @Test(priority = 3)
    public void openSideBar3() {
        // Open sidebar
        homePage.openSideBar();

        // Open Dynamic Catalog
        homePage.clickDynamicCatalog();

        // Open Slider
        homePage.clickSlider();

        // Verify destination URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-slider.html");
    }

    @Test(priority = 4)
    public void dropdownTest1(){
        //check dropdown displayed and activated
        Assert.assertTrue(homePage.isProductSortDisplayed());
        Assert.assertTrue(homePage.isProductSortEnabled());

        //check dropdown option
        Assert.assertEquals(homePage.getProductSortOptionsCount(), 4);
        Assert.assertEquals(homePage.getSelectedProductSortText(), "Name (A to Z)");
    }

    @Test(priority = 5)
    public void dropdownTest2() {
        // Select Name (Z to A)
        homePage.selectProductSort("za");

        // Verify selected option
        Assert.assertEquals(homePage.getSelectedProductSortText(), "Name (Z to A)");
    }

    @Test(priority = 6)
    public void dropdownTest3() {
        // Select Price (low to high)
        homePage.selectProductSort("lohi");

        // Verify selected option
        Assert.assertEquals(homePage.getSelectedProductSortText(), "Price (low to high)");
    }

    @Test(priority = 7)
    public void dropdownTest4() {
        // Select Price (high to low)
        homePage.selectProductSort("hilo");

        // Verify selected option
        Assert.assertEquals(homePage.getSelectedProductSortText(), "Price (high to low)");
    }

    @Test(priority = 8)
    public void fakeShopping() {

        // Add product to cart
        homePage.addProductToCart("sauce-labs-backpack");

        // Verify cart badge
        Assert.assertTrue(homePage.isShoppingCartBadgeDisplayed());
        Assert.assertEquals(homePage.getShoppingCartBadge(), "1");

        // Open shopping cart
        homePage.clickShoppingCart();

        // Verify cart page URL
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html")), "User did not navigate to cart page");

        // Verify cart title
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart");

        // Remove product from cart
        cartPage.removeCartItem();
    }


    @Test(priority = 9)
    public void shoppingOneProduct() {
        // Add product to cart
        homePage.addProductToCart("sauce-labs-backpack");

        // Open shopping cart
        homePage.clickShoppingCart();

        // Verify cart product
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart");
        Assert.assertEquals(cartPage.getCartItemName(0), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemDescription(0), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(cartPage.getCartItemPrice(0), "$29.99");

        // Checkout
        cartPage.clickCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        // Fill the form
        checkoutPage.enterFirstName("Aksal");
        checkoutPage.enterLastName("Pratama");
        checkoutPage.enterPostalCode("123");
        checkoutPage.clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        // Verify overview checkout title
        Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Overview");

        // Verify overview product, description & price
        Assert.assertEquals(checkoutPage.getCheckoutItemName(0), "Sauce Labs Backpack");
        Assert.assertEquals(checkoutPage.getCheckoutItemDescription(0), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(checkoutPage.getCheckoutItemPrice(0), "$29.99");

        // Payment information
        Assert.assertTrue(checkoutPage.isPaymentInfoLabelDisplayed());
        Assert.assertTrue(checkoutPage.isPaymentInfoValueDisplayed());

        // Shipping information
        Assert.assertTrue(checkoutPage.isShippingInfoLabelDisplayed());
        Assert.assertTrue(checkoutPage.isShippingInfoValueDisplayed());

        // Subtotal, tax & total
        Assert.assertEquals(checkoutPage.getSubtotalLabel(), "Item total: $29.99");
        Assert.assertTrue(checkoutPage.getTaxLabel().startsWith("Tax: $"));
        Assert.assertTrue(checkoutPage.getTotalLabel().startsWith("Total: $"));


        // Finish button
        checkoutPage.clickFinish();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        // Verify checkout complete title
        Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Complete!");

        // Verify Pony Express image
        Assert.assertTrue(checkoutPage.isPonyExpressDisplayed(), "Pony Express image is not displayed");

        // Verify completion message
        Assert.assertEquals(checkoutPage.getCompleteHeader(), "Thank you for your order!");
        Assert.assertEquals(checkoutPage.getCompleteText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        // Verify buttons
        Assert.assertTrue(checkoutPage.isBackToProductsDisplayed());
        Assert.assertTrue(checkoutPage.isBackToProductsEnabled());
        Assert.assertTrue(checkoutPage.isGeneratePdfOrderDisplayed());
        Assert.assertTrue(checkoutPage.isGeneratePdfOrderEnabled());
        checkoutPage.clickBackToProducts();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 10)
    public void shoppingMultipleProduct() {
        // Add multiple products to cart
        homePage.addProductToCart("sauce-labs-backpack");
        homePage.addProductToCart("sauce-labs-bike-light");
        homePage.addProductToCart("sauce-labs-bolt-t-shirt");

        // Verify cart badge
        Assert.assertTrue(homePage.isShoppingCartBadgeDisplayed());
        Assert.assertEquals(homePage.getShoppingCartBadge(), "3");

        // Open shopping cart
        homePage.clickShoppingCart();

        // Verify cart quantity
        Assert.assertEquals(cartPage.getCartItemCount(), 3);

        // Verify all products
        Assert.assertEquals(cartPage.getCartItemName(0), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemName(1), "Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.getCartItemName(2), "Sauce Labs Bolt T-Shirt");

        // Verify initial product prices
        Assert.assertEquals(cartPage.getCartItemPrice(0), "$29.99");
        Assert.assertEquals(cartPage.getCartItemPrice(1), "$9.99");
        Assert.assertEquals(cartPage.getCartItemPrice(2), "$15.99");

        // Remove Sauce Labs Bike Light
        cartPage.removeCartItem("sauce-labs-bike-light");

        // Verify cart quantity after removal
        Assert.assertEquals(cartPage.getCartItemCount(), 2);
        Assert.assertEquals(homePage.getShoppingCartBadge(), "2");

        // Verify remaining products
        Assert.assertEquals(cartPage.getCartItemName(0), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemName(1), "Sauce Labs Bolt T-Shirt");

        // Verify remaining product prices
        Assert.assertEquals(cartPage.getCartItemPrice(0), "$29.99");
        Assert.assertEquals(cartPage.getCartItemPrice(1), "$15.99");

        // Checkout
        cartPage.clickCheckout();
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");

        // Fill checkout information
        checkoutPage.enterFirstName("Aksal");
        checkoutPage.enterLastName("Pratama");
        checkoutPage.enterPostalCode("123");
        checkoutPage.clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html");

        // Verify checkout overview
        Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Overview");
        Assert.assertEquals(checkoutPage.getCheckoutItemName(0), "Sauce Labs Backpack");
        Assert.assertEquals(checkoutPage.getCheckoutItemName(1), "Sauce Labs Bolt T-Shirt");

        // Verify product prices
        Assert.assertEquals(checkoutPage.getCheckoutItemPrice(0), "$29.99");
        Assert.assertEquals(checkoutPage.getCheckoutItemPrice(1), "$15.99");

        // Payment information
        Assert.assertTrue(checkoutPage.isPaymentInfoLabelDisplayed());
        Assert.assertTrue(checkoutPage.isPaymentInfoValueDisplayed());

        // Shipping information
        Assert.assertTrue(checkoutPage.isShippingInfoLabelDisplayed());
        Assert.assertTrue(checkoutPage.isShippingInfoValueDisplayed());

        // Subtotal, tax, and total
        Assert.assertEquals(checkoutPage.getSubtotalLabel(), "Item total: $45.98");
        Assert.assertTrue(checkoutPage.getTaxLabel().startsWith("Tax: $"));
        Assert.assertTrue(checkoutPage.getTotalLabel().startsWith("Total: $"));

        // Finish checkout
        checkoutPage.clickFinish();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");

        // Verify checkout completion
        Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout: Complete!");
        Assert.assertTrue(checkoutPage.isPonyExpressDisplayed());
        Assert.assertEquals(checkoutPage.getCompleteHeader(), "Thank you for your order!");
        Assert.assertEquals(checkoutPage.getCompleteText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        // Verify buttons
        Assert.assertTrue(checkoutPage.isBackToProductsDisplayed());
        Assert.assertTrue(checkoutPage.isBackToProductsEnabled());
        Assert.assertTrue(checkoutPage.isGeneratePdfOrderDisplayed());
        Assert.assertTrue(checkoutPage.isGeneratePdfOrderEnabled());

        // Back to products
        checkoutPage.clickBackToProducts();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 11)
    public void logoutSuccessful() {
        // Open sidebar
        homePage.openSideBar();

        // Verify sidebar is displayed
        Assert.assertTrue(homePage.isSidebarDisplayed(), "Sidebar menu is not displayed");

        // Click logout
        homePage.clickLogout();

        // Verify redirected to login page
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/")), "Logout failed: URL is incorrect");

        // Verify login button
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");
    }

    @Test(priority = 12)
    public void verifyLoginPageAfterLogout() {
        // Open sidebar
        homePage.openSideBar();

        // Click logout
        homePage.clickLogout();

        // Verify redirected to login page
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/")), "Logout failed: URL is incorrect");

        // Verify username field
        Assert.assertTrue(loginPage.isUsernameDisplayed(), "Username field is not displayed");

        // Verify password field
        Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password field is not displayed");

        // Verify login button
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");

        // Verify login button enabled
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button is not enabled");
    }

    @Test(priority = 13)
    public void verifyCannotAccessProductsAfterLogout() {
        // Open sidebar
        homePage.openSideBar();

        // Logout
        homePage.clickLogout();

        // Verify redirected to login page
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/")), "Logout failed: URL is incorrect");

        // Navigate back
        driver.navigate().back();

        // Verify user cannot access Products page
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/")), "User can access Products page after logout");

        // Refresh page
        driver.navigate().refresh();

        // Verify login page remains displayed
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/")), "User can access Products page after refresh");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login page is not displayed after refresh");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
