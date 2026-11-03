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

import java.time.Duration;

public class HomePageTest {
    WebDriver driver;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html")), "Login failed");
    }

    @Test(priority = 0)
    public void checkElement() {
        //check element header app logo
        Assert.assertEquals(driver.findElement(By.className("app_logo")).getText(), "Swag Labs");

        //check element title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");

        //check element product 1
        Assert.assertTrue(driver.findElement(By.id("item_4_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        //check element product 2
        Assert.assertTrue(driver.findElement(By.id("item_0_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_0_title_link")).getText(), "Sauce Labs Bike Light");
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

        //check element product 3
        Assert.assertTrue(driver.findElement(By.id("item_1_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_1_title_link")).getText(), "Sauce Labs Bolt T-Shirt");
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();

        //check element product 4
        Assert.assertTrue(driver.findElement(By.id("item_5_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_5_title_link")).getText(), "Sauce Labs Fleece Jacket");
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();

        //check element product 5
        Assert.assertTrue(driver.findElement(By.id("item_2_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_2_title_link")).getText(), "Sauce Labs Onesie");
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("remove-sauce-labs-onesie")).click();

        //check element product 6
        Assert.assertTrue(driver.findElement(By.id("item_3_img_link")).isDisplayed(), "Gambar tidak berhasil ditampilkan");
        Assert.assertEquals(driver.findElement(By.id("item_3_title_link")).getText(), "Test.allTheThings() T-Shirt (Red)");
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();

        //check element all of products item desc
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(0).getText(),
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(1).getText(),
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(2).getText(),
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(3).getText(),
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(4).getText(),
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(5).getText(),
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.");

        //check element all of products price
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(0).getText(), "$29.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(1).getText(), "$9.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(2).getText(), "$15.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(3).getText(), "$49.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(4).getText(), "$7.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(5).getText(), "$15.99");
    }

    @Test(priority = 1)
    public void openSideBar1() {
        //open and check lazy load on the sidebar
        driver.findElement(By.id("react-burger-menu-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement catalogLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_sidebar_link")));
        catalogLink.click();

        WebElement lazyloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_lazy_load_link")));
        lazyloadLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-lazy-load.html");
    }

    @Test(priority = 2)
    public void openSideBar2() {
        //open and check spinner on the sidebar
        driver.findElement(By.id("react-burger-menu-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement catalogLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_sidebar_link")));
        catalogLink.click();

        WebElement spinnerLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_spinner_link")));
        spinnerLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-spinner.html");
    }

    @Test(priority = 3)
    public void openSideBar3() {
        //open and check lazy load on the sidebar
        driver.findElement(By.id("react-burger-menu-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement catalogLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_sidebar_link")));
        catalogLink.click();

        WebElement sliderLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("dynamic_catalog_slider_link")));
        sliderLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/dynamic-catalog-slider.html");
    }

    @Test(priority = 4)
    public void dropdownTest1(){
        //check dropdown displayed and activated
        WebElement checkDropdown = driver.findElement(By.cssSelector("[data-test='product-sort-container']"));
        Assert.assertTrue(checkDropdown.isDisplayed(),"Dropdown is not displayed");
        Assert.assertTrue(checkDropdown.isEnabled(), "Dropdown is not activated");

        //check dropdown option
        Select sort = new Select(checkDropdown);
        Assert.assertEquals(sort.getOptions().size(),4, "Options dropdown is not correct");
        Assert.assertEquals(sort.getFirstSelectedOption().getText(), "Name (A to Z)", "default option is false");
    }

    @Test(priority = 5)
    public void dropdownTest2() {
        By dropdownLocator = By.cssSelector("[data-test='product-sort-container']");
        WebElement dropdown = driver.findElement(dropdownLocator);

        // Select option
        new Select(dropdown).selectByValue("za");

        // Find dropdown again
        WebElement updatedDropdown = driver.findElement(dropdownLocator);

        // Verify selected option
        String actualOption = new Select(updatedDropdown).getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption, "Name (Z to A)", "Selected option is incorrect");
    }

    @Test(priority = 5)
    public void dropdownTest3() {
        By dropdownLocator = By.cssSelector("[data-test='product-sort-container']");
        WebElement dropdown = driver.findElement(dropdownLocator);

        // Select option
        new Select(dropdown).selectByValue("lohi");

        // Find dropdown again
        WebElement updatedDropdown = driver.findElement(dropdownLocator);

        // Verify selected option
        String actualOption = new Select(updatedDropdown).getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption, "Price (low to high)", "Selected option is incorrect");
    }

    @Test(priority = 6)
    public void dropdownTest4() {
        By dropdownLocator = By.cssSelector("[data-test='product-sort-container']");
        WebElement dropdown = driver.findElement(dropdownLocator);

        // Select option
        new Select(dropdown).selectByValue("hilo");

        // Find dropdown again
        WebElement updatedDropdown = driver.findElement(dropdownLocator);

        // Verify selected option
        String actualOption = new Select(updatedDropdown).getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption, "Price (high to low)", "Selected option is incorrect");
    }

    @Test(priority = 7)
    public void fakeShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //add to cart then remove
        By addToCart = By.id("add-to-cart-sauce-labs-backpack");

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCart));

        // Add product to cart
        addButton.click();

        // Verify cart badge
        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1", "Cart quantity is incorrect");
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        // Verify cart page URL
        Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html")), "User did not navigate to cart page");
        // Remove product from cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-backpack"))).click();
    }

    @Test(priority = 8)
    public void shoppingOneProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //add to cart
        By addToCart = By.id("add-to-cart-sauce-labs-backpack");
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addButton.click();

        //verify cart badge if only one product in the cart
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();

        //verify product
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(),"Sauce Labs Backpack");
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).getText(), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        //checkout
        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        //fill the form
        driver.findElement(By.id("first-name")).sendKeys("Aksal");
        driver.findElement(By.id("last-name")).sendKeys("Pratama");
        driver.findElement(By.id("postal-code")).sendKeys("123");
        driver.findElement(By.id("continue")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        //verify overview checkout title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Overview");
        //verify overview checkout product, desc & price
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_desc")).get(0).getText(),
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(0).getText(), "$29.99");
        //payment info & value label
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='payment-info-label']")).isDisplayed(), "Payment Info Label is not show");
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='payment-info-value']")).isDisplayed(), "Payment Value Label is not show");
        //shipping info label
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='shipping-info-label']")).isDisplayed(), "Shipping Info Label is not show");
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='shipping-info-value']")).isDisplayed(), "Shipping Value Label is not show");
        //subtotal info
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText(), "Item total: $29.99");
        //tax info
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='tax-label']")).getText().startsWith("Tax: $"));
        //total info
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='total-label']")).getText().startsWith("Total: $"));
        //finish button
        Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("finish")).isEnabled());
        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        //verify checkout complete title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Complete!");
        //verify img
        WebElement ponyExpress = driver.findElement(By.cssSelector("[data-test='pony-express']"));
        Assert.assertTrue(ponyExpress.isDisplayed(), "Pony Express image is not displayed");
        //verify h2
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!");
        //complete text
        Assert.assertEquals(driver.findElement(By.className("complete-text")).getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        //complete button
        Assert.assertTrue(driver.findElement(By.id("back-to-products")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("back-to-products")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("generate-pdf-order")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("generate-pdf-order")).isEnabled());
        driver.findElement(By.id("back-to-products")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void shoppingMultipleProduct(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //add multiple products
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        //verify cart badge
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText(), "3");

        //open cart
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();

        //verify cart quantity
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 3);

        //verify all products
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        Assert.assertEquals(driver.findElement(By.id("item_0_title_link")).getText(), "Sauce Labs Bike Light");
        Assert.assertEquals(driver.findElement(By.id("item_1_title_link")).getText(), "Sauce Labs Bolt T-Shirt");

        //verify initial subtotal
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(0).getText(), "$29.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(1).getText(), "$9.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(2).getText(), "$15.99");

        //remove one product
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

        //verify cart quantity after remove
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2);
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText(), "2");

        //verify remaining products
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        Assert.assertEquals(driver.findElement(By.id("item_1_title_link")).getText(), "Sauce Labs Bolt T-Shirt");

        //verify removed product
        Assert.assertEquals(driver.findElements(By.id("item_0_title_link")).size(), 0);

        //verify subtotal after remove
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(0).getText(), "$29.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(1).getText(), "$15.99");

        //checkout
        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        //fill the form
        driver.findElement(By.id("first-name")).sendKeys("Aksal");
        driver.findElement(By.id("last-name")).sendKeys("Pratama");
        driver.findElement(By.id("postal-code")).sendKeys("123");
        driver.findElement(By.id("continue")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        //verify overview checkout title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Overview");

        //verify overview checkout product
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2);
        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        Assert.assertEquals(driver.findElement(By.id("item_1_title_link")).getText(), "Sauce Labs Bolt T-Shirt");

        //verify product price
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(0).getText(), "$29.99");
        Assert.assertEquals(driver.findElements(By.className("inventory_item_price")).get(1).getText(), "$15.99");

        //payment info & value label
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='payment-info-label']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='payment-info-value']")).isDisplayed());

        //shipping info & value label
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='shipping-info-label']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='shipping-info-value']")).isDisplayed());

        //subtotal info
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText(), "Item total: $45.98");

        //tax info
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='tax-label']")).getText().startsWith("Tax: $"));

        //total info
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='total-label']")).getText().startsWith("Total: $"));

        //finish button
        Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("finish")).isEnabled());
        driver.findElement(By.id("finish")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        //verify checkout complete title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Complete!");

        //verify img
        WebElement ponyExpress = driver.findElement(By.cssSelector("[data-test='pony-express']"));
        Assert.assertTrue(ponyExpress.isDisplayed(), "Pony Express image is not displayed");

        //verify h2
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!");

        //complete text
        Assert.assertEquals(driver.findElement(By.className("complete-text")).getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        //complete button
        Assert.assertTrue(driver.findElement(By.id("back-to-products")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("back-to-products")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("generate-pdf-order")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("generate-pdf-order")).isEnabled());

        driver.findElement(By.id("back-to-products")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
