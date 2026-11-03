package TryTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTesting {
    WebDriver driver;

    @BeforeTest
    private void init(){
        //initiate browser
        System.setProperty("webdriver.chrome.driver","D:/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        //go to login page
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    private void checkElement(){
        //check element h1
        Assert.assertEquals(driver.findElement(By.cssSelector("header h1")).getText(), "CURA Healthcare Service");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("header h1")).getCssValue("color")).asHex(), "#ffffff");

        //check element h3
        Assert.assertEquals(driver.findElement(By.cssSelector("header h3")).getText(), "We Care About Your Health");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("header h3")).getCssValue("color")).asHex(), "#4fb6e7");

        //check element btn
        Assert.assertEquals(driver.findElement(By.id("btn-make-appointment")).getText(), "Make Appointment");
        Assert.assertEquals(driver.findElement(By.id("btn-make-appointment")).getCssValue("background-color"), "rgba(115, 112, 181, 0.8)");

        //check element menu-toggle
        Assert.assertEquals(driver.findElement(By.id("menu-toggle")).getCssValue("background-color"), "rgba(115, 112, 181, 0.8)");
    }

    @Test (priority = 1)
    private void clickToggleMenu(){
        driver.findElement(By.id("menu-toggle")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[1]")).getText(), "CURA Healthcare");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[2]")).getText(), "Home");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]")).getText(), "Login");
        driver.findElement(By.id("menu-toggle")).click();
    }

    @Test (priority = 2)
    private void clickButtonMakeAppointment(){
        driver.findElement(By.id("btn-make-appointment")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/profile.php#login");
    }

    @AfterTest
    private void closeBrowser(){
        driver.quit();
    }
}
