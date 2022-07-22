package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.AssignLeavePage;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class AssignLeaveTest {

    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;
    AssignLeavePage assignLeavePage;

    //@BeforeSuite
    @BeforeMethod
    public void setUp(){
        basePage = new BasePage();
        loginPage = basePage.setupDriver();
        homePage = loginPage.loginUsingValidCredentials("Admin","admin123");
        assignLeavePage = homePage.navigateToLeavePage();
        Assert.assertEquals(assignLeavePage.getPageHeder(),"Assign Leave");
    }

    @AfterMethod
    public void closeBrowser(){
        basePage.teardown();
    }

//    @BeforeMethod
//    public void navigateToLeavePage (){
//        assignLeavePage = homePage.navigateToLeavePage();
//        Assert.assertEquals(assignLeavePage.getPageHeder(),"Assign Leave");
//    }
//
//    @AfterMethod
//    public void navigateToDashbord(){
//        homePage.clickOnDashboardBtn();
//    }



    @Test(priority = 2,enabled = true)
    public void assignALeave(){
        String empName = basePage.getPropertyDetail("EmpName");
        String leaveType = basePage.getPropertyDetail("leaveType");
        assignLeavePage.assignLeave(empName,leaveType,"2020-07-10","2020-07-10","Full Day","test comment","ok");
    }

    // verify validation messages before enter the details
    @Test(priority = 1)
    public void requiredFieldsValidation(){
        // one click Invalid
        assignLeavePage.clickAssignBtn();
        Assert.assertEquals(assignLeavePage.getEmpNameValidationMessage(),"Invalid");
        Assert.assertEquals(assignLeavePage.getLeaveTypeValidationMessage(),"Required");
        Assert.assertEquals(assignLeavePage.getFromDateTypeValidationMessage(),"Should be a valid date in yyyy-mm-dd format");
        Assert.assertEquals(assignLeavePage.getToDateValidationMessage(),"Should be a valid date in yyyy-mm-dd format");
        // click second time
        assignLeavePage.clickAssignBtn();
        Assert.assertEquals(assignLeavePage.getEmpNameValidationMessage(),"Required");
    }


}
