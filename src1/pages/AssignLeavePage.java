package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AssignLeavePage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public AssignLeavePage(WebDriver driver){
        this.driver =driver;
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(lblHeader)));
    }

    private static final By lblHeader = By.xpath("//h1[contains(text(),'Assign Leave')]");
    private static final By txtEmpName = By.id("assignleave_txtEmployee_empName");
    private static final By empName_validation = By.xpath("//input[@id='assignleave_txtEmployee_empName']/ancestor::li/span");
    private static final By ddLeaveType = By.id("assignleave_txtLeaveType");
    private static final By leaveType_validation = By.xpath("//select[@id='assignleave_txtLeaveType']/ancestor::li/span");
    private static final By dpFromDate = By.id("assignleave_txtFromDate");
    private static final By fromDate_validation = By.xpath("//input[@id='assignleave_txtFromDate']/ancestor::li/span");
    private static final By dpToDate = By.id("assignleave_txtToDate");
    private static final By toDate_validation = By.xpath("//input[@id='assignleave_txtToDate']/ancestor::li/span");
    private static final By txtComment = By.id("assignleave_txtComment");
    private static final By btnAssign = By.id("assignBtn");
    private static final By selDuration = By.xpath("//select[@id='assignleave_duration_duration']");
    private static final By selPartialDays = By.xpath("//select[@id='assignleave_partialDays']");

    //OrangeHRM - Confirm Leave Assignment popup
    private static final By popupHeader = By.xpath("//h3[contains(text(),'OrangeHRM - Confirm Leave Assignment')]");
    private static final By popupOkBtn = By.xpath("//input[@id='confirmOkButton']");
    private static final By popupCancelBtn = By.xpath("//input[@id='confirmCancelButton']");


    public String getPageHeder(){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lblHeader)));
        return driver.findElement(lblHeader).getText();
    }

    public void selectEmpName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(txtEmpName));
        driver.findElement(txtEmpName).sendKeys(name+ Keys.ENTER);
    }

    public void selectLeaveType(String type){
        wait.until(ExpectedConditions.elementToBeClickable(ddLeaveType));
        Select dropdown = new Select(driver.findElement(ddLeaveType));
        dropdown.selectByVisibleText(type);
    }

    public void setFromDate(String date){
        WebElement setDate  = driver.findElement(dpFromDate);
        wait.until(ExpectedConditions.elementToBeClickable(dpFromDate));
        setDate.clear();
        setDate.sendKeys(date + Keys.ENTER);
    }

    public void setToDate(String date){
        WebElement setDate  = driver.findElement(dpToDate);
        wait.until(ExpectedConditions.elementToBeClickable(dpToDate));
        setDate.clear();
        setDate.sendKeys(date+Keys.ENTER);
    }

    //when from date and to date is equal
    public void  selectDuration(String duration){
        wait.until(ExpectedConditions.elementToBeClickable(selDuration));
        Select dropdown = new Select(driver.findElement(selDuration));

        // verify all the options in the dropdown
        String[] exp = {"Full Day", "Half Day", "Specify Time"};
        List<WebElement> options = dropdown.getOptions();
        int count = 0;
        for(WebElement we:options) {
            for (int i=0; i<exp.length; i++){
                if (we.getText().equals(exp[i])){
                    count++;
                }
            }
        }
        if (count != exp.length) {
            System.out.println("select Duration dropdown options not matched");
        }

        // select duration
        dropdown.selectByVisibleText(duration);
    }

    //when from date and to date is not equal
    public void  selectPartialDays(String partialDays){
        wait.until(ExpectedConditions.elementToBeClickable(selPartialDays));
        Select dropdown = new Select(driver.findElement(selPartialDays));
        dropdown.selectByVisibleText(partialDays);

    }

    public void enterComments(String comments){
        wait.until(ExpectedConditions.elementToBeClickable(txtComment));
        driver.findElement(txtComment).sendKeys(comments);
    }

    public void clickAssignBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(btnAssign));
        driver.findElement(btnAssign).click();
    }

    public void confirmationPopup(String clickOption){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(popupHeader)));
        if (clickOption.equalsIgnoreCase("Ok")){
            driver.findElement(popupOkBtn).click();
        } else {
            driver.findElement(popupCancelBtn).click();
        }
    }

    public String getEmpNameValidationMessage(){
        return driver.findElement(empName_validation).getText();
    }

    public String getLeaveTypeValidationMessage(){
        return driver.findElement(leaveType_validation).getText();
    }

    public String getFromDateTypeValidationMessage(){
        return driver.findElement(fromDate_validation).getText();
    }

    public String getToDateValidationMessage(){
        return driver.findElement(toDate_validation).getText();
    }


    public void assignLeave(String name, String leaveType, String fromDate, String toDate, String duration, String comments, String submit){
        this.selectEmpName(name);
        this.selectLeaveType(leaveType);
        this.setFromDate(fromDate);
        this.setToDate(toDate);
        this.selectDuration(duration);
        this.enterComments(comments);
        this.clickAssignBtn();
        this.confirmationPopup(submit);
    }


}
