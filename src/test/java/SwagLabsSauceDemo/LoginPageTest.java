package SwagLabsSauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import SwagLabsSauceDemo.pages.LoginPage;

import java.time.Duration;

public class LoginPageTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @BeforeMethod
    public void init(){
        //initiate browser
        System.setProperty("webdriver.chrome.driver","D:/WebDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //go to login page
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver, wait);
    }

    @Test(priority = 0)
    public void checkElement() {
        // Check element Swag Labs
        Assert.assertEquals(loginPage.getLoginLogoText(), "Swag Labs");

        // Check username placeholder
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), "Username");

        // Check password placeholder
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), "Password");

        // Check login button
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button seharusnya terlihat");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button seharusnya bisa dipakai");
        Assert.assertEquals(loginPage.getLoginButtonValue(), "Login");
    }

    @Test(priority = 1)
    public void LoginWithEmptyCredentials() {
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 2)
    public void LoginWithEmptyPassword() {
        loginPage.enterUsername("standard_user");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(priority = 3)
    public void LoginWithWrongUsername(){
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 4)
    public void LoginWithWrongPassword(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 5)
    public void LoginWithWrongCredentials(){
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 6)
    public void LoginWithCorrectCredentials() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}