package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver =driver;
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(txtWelcome));
    }

    private static final By txtWelcome = By.id("welcome");
    private static final By lblDashboard = By.xpath("//h1[contains(text(),'Dashboard')]");
    private static final By ddLeave = By.xpath("//b[contains(text(),'Leave')]");
    private static final By subMenuAssignLeave =By.xpath("//a[@id='menu_leave_assignLeave']");

    public String getWelcomeMessage(){
        wait.until(ExpectedConditions.elementToBeClickable(txtWelcome));
        return driver.findElement(txtWelcome).getText();
    }

    public AssignLeavePage navigateToLeavePage(){
        wait.until(ExpectedConditions.elementToBeClickable(ddLeave));
        driver.findElement(ddLeave).click();
        wait.until(ExpectedConditions.elementToBeClickable(subMenuAssignLeave));
        driver.findElement(subMenuAssignLeave).click();
        return new AssignLeavePage(driver);
    }

    public void clickOnDashboardBtn(){
        driver.findElement(By.xpath("//b[contains(text(),'Dashboard')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(lblDashboard)));
    }

}
