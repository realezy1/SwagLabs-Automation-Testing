package TryTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AppointmentTestPage {
    WebDriver driver;

    @BeforeTest
    private void init() {
        //initiate browser
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=D:/SeleniumChromeProfile");

        driver = new ChromeDriver(options);
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
        //login
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/#appointment");
    }

    @Test(priority = 0)
    private void checkElement(){
        //check element h2
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Make Appointment");

        //check element option values (can use this)
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[1]")).getText(), "Tokyo CURA Healthcare Center");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[2]")).getText(), "Hongkong CURA Healthcare Center");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[3]")).getText(), "Seoul CURA Healthcare Center");

        //check element option values (or this)
        //Select dropdownFacility = new Select(driver.findElement(By.id("combo_facility")));
        //List<WebElement> dropdownOption = dropdownFacility.getOptions();
        //Assert.assertEquals(dropdownOption.get(0).getAttribute("value"), "Tokyo CURA Healthcare Center");
        //Assert.assertEquals(dropdownOption.get(1).getAttribute("value"), "Hongkong CURA Healthcare Center");
        //Assert.assertEquals(dropdownOption.get(2).getAttribute("value"), "Seoul CURA Healthcare Center");

        //check element text box area
        Assert.assertEquals(driver.findElement(By.id("txt_comment")).getAttribute("placeholder"), "Comment");
    }

    @Test(priority = 1)
    private void FillWithCorrectValues(){
        //dropdown
        Select dropdownFacility = new Select(driver.findElement(By.id("combo_facility")));
        dropdownFacility.selectByValue("Tokyo CURA Healthcare Center");

        //check box
        driver.findElement(By.id("chk_hospotal_readmission")).click();

        //radio button
        driver.findElement(By.id("radio_program_medicare")).click();

        //date picker
        driver.findElement(By.id("txt_visit_date")).sendKeys("15/09/2023");

        //text area
        driver.findElement(By.id("txt_comment")).sendKeys("This Test is Successfull");

        //button book appointment
        driver.findElement(By.id("btn-book-appointment")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/appointment.php#summary");
    }

    @Test(priority = 2, dependsOnMethods = {"FillWithCorrectValues"})
    private void checkAppointmentSummary(){
        //check element h2
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Appointment Confirmation");

        //check element p
        Assert.assertEquals(driver.findElement(By.cssSelector("section p")).getText(), "Please be informed that your appointment has been booked as following:");

        //check facility summary
        Assert.assertEquals(driver.findElement(By.id("facility")).getText(),"Tokyo CURA Healthcare Center");

        //check checkbox summary
        Assert.assertEquals(driver.findElement(By.id("hospital_readmission")).getText(),"Yes");

        //check radio button summary
        Assert.assertEquals(driver.findElement(By.id("program")).getText(),"Medicare");

        //check date picker summary
        Assert.assertEquals(driver.findElement(By.id("visit_date")).getText(),"15/09/2023");

        //check text area summary
        Assert.assertEquals(driver.findElement(By.id("comment")).getText(),"This Test is Successfull");

        //goto homepage
        driver.findElement(By.className("btn-default")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterTest
    private void closeBrowser(){
        driver.quit();
    }
}