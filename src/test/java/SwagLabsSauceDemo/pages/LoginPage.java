
package SwagLabsSauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    private final By loginLogo = By.className("login_logo");
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By errorButton = By.className("error-button");

    // Get element information
    public String getLoginLogoText() {
        return driver.findElement(loginLogo).getText();
    }

    public String getUsernamePlaceholder() {
        return driver.findElement(usernameField).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return driver.findElement(passwordField).getAttribute("placeholder");
    }

    public boolean isUsernameDisplayed() {
        return driver.findElement(usernameField).isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return driver.findElement(passwordField).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }

    public String getLoginButtonValue() {
        return driver.findElement(loginButton).getAttribute("value");
    }

    // User interactions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void closeErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(errorButton)).click();
    }


}