package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    WebDriver driver;
    static WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='logInPanelHeading']"))));
    }

    private static final By txtUsername = By.id("txtUsername");
    private static final By txtPassword = By.id("txtPassword");
    private static final By btnLogin = By.id("btnLogin");
    private static final By linkForgotPassword = By.xpath("//div[@id='forgotPasswordLink']/a");
    private static final By lblSpanMessage = By.id("spanMessage");


    public void typeUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(txtUsername));
        driver.findElement(txtUsername).sendKeys(username);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(txtPassword));
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        driver.findElement(btnLogin).click();
    }


    public String errorMessage(){
        wait.until(ExpectedConditions.elementToBeClickable(lblSpanMessage));
        return driver.findElement(lblSpanMessage).getText();
    }

    public HomePage loginUsingValidCredentials(String username, String password){
        this.typeUsername(username);
        this.typePassword(password);
        this.clickLogin();
        return new HomePage(driver);
    }

    public void enterInvalidCredentials(String username, String password){
        this.typeUsername(username);
        this.typePassword(password);
        this.clickLogin();
    }

}
