package SwagLabsSauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void init(){
        //initiate browser
        System.setProperty("webdriver.chrome.driver","D:/WebDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //go to login page
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(priority = 0)
    public void checkElement(){
        //check element swag labs
        Assert.assertEquals(driver.findElement(By.className("login_logo")).getText(), "Swag Labs");

        //check element username
        Assert.assertEquals(driver.findElement(By.id("user-name")).getAttribute("placeholder"), "Username");

        //check element password
        Assert.assertEquals(driver.findElement(By.id("password")).getAttribute("placeholder"), "Password");

        //check element login button
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login button seharusnya terlihat");
        Assert.assertTrue(driver.findElement(By.id("login-button")).isEnabled(), "Login button seharusnya bisa dipakai");
        Assert.assertEquals(driver.findElement(By.id("login-button")).getAttribute("value"), "Login");
    }

    @Test(priority = 1)
    public void LoginWithNullValue(){
        //check if usn & pw is null values
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), "Epic sadface: Username is required");
        driver.findElement(By.className("error-button")).click();

        // Check if username is null
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), "Epic sadface: Password is required");
        //Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText(), "Epic sadface: Username is required");
        driver.findElement(By.className("error-button")).click();
        driver.findElement(By.id("user-name")).clear();

        // Check if password is null
        //driver.findElement(By.id("password")).clear();

        //driver.findElement(By.id("user-name")).clear();
        //driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // Verify password is empty
        //System.out.println("Username: [" +driver.findElement(By.id("user-name")).getAttribute("value") + "]");
        //System.out.println("Password: [" + driver.findElement(By.id("password")).getAttribute("value") + "]");
        //Assert.assertEquals(driver.findElement(By.id("password")).getAttribute("value"), "");
        //driver.findElement(By.id("login-button")).click();
        //Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText(),"Epic sadface: Password is required");
    }

    @Test(priority = 2)
    public void LoginWithWrongValue(){
        //check if use wrong username
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("user-name")).sendKeys("wleowleo");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), "Epic sadface: Username and password do not match any user in this service");
        driver.findElement(By.className("error-button")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();

        //check if use wrong password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_saucee");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), "Epic sadface: Username and password do not match any user in this service");
        driver.findElement(By.className("error-button")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();

        //check if use wrong usn & pw
        driver.findElement(By.id("user-name")).sendKeys("wleowleo");
        driver.findElement(By.id("password")).sendKeys("secret_saucee");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), "Epic sadface: Username and password do not match any user in this service");
        driver.findElement(By.className("error-button")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
    }

    @Test(priority = 3)
    public void LoginWithCorrectValue() {
        driver.findElement(By.id("user-name")).clear();

        By passwordLocator = By.id("password");
        driver.findElement(passwordLocator).clear();

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(passwordLocator).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}