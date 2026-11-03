package TryTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest {
    WebDriver driver;

    @BeforeTest
    private void init() {
        //initiate browser
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        //go to login page
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/profile.php#login");
    }

    @Test(priority = 0)
    private void checkElement(){
        //check element h2
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Login");

        //check element p
        Assert.assertEquals(driver.findElement(By.cssSelector("section p")).getText(), "Please login to make appointment.");

        //check element text box
        Assert.assertEquals(driver.findElement(By.id("txt-username")).getAttribute("placeholder"), "Username");
        Assert.assertEquals(driver.findElement(By.id("txt-password")).getAttribute("placeholder"), "Password");
    }

    @Test(priority = 1)
    private void LoginWithNullValue(){
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(), "Login failed! Please ensure the username and password are valid.");
    }

    @Test(priority = 2)
    private void LoginWithWrongValue(){
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("WrongValues");
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(), "Login failed! Please ensure the username and password are valid.");
    }

    @Test(priority = 3)
    private void LoginWWithCorrectValue(){
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/#appointment");
    }

    @AfterTest
    private void closeBrowser(){
        driver.quit();
    }
}
