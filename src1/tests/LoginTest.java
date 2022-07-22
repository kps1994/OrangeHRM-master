package tests;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {

    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        basePage = new BasePage();
        loginPage = basePage.setupDriver();
    }

    @AfterMethod
    public void closeBrowser(){
        basePage.teardown();
    }

    @Test
    //Verify that user is able to login with valid username and password.
    public void loginToTheSystemWithValidCredentials() {
        homePage = loginPage.loginUsingValidCredentials("Admin","admin123");
        Assert.assertTrue(homePage.getWelcomeMessage().contains("Welcome"));
    }

    @Test (priority = 1)
    //Verify that user is not able to login with invalid username and valid Password.
    public void invalidUsernameWithValidPassword() {
        loginPage.enterInvalidCredentials("Admin1","admin123");
        Assert.assertEquals(loginPage.errorMessage(),"Invalid credentials");
    }

    @Test(priority = 2)
    //Verify that user is not able to login with Valid username and invalid Password.
    public void validUsernameWithInvalidPassword() {
        loginPage.enterInvalidCredentials("Admin","admin1234");
        Assert.assertEquals(loginPage.errorMessage(),"Invalid credentials");
    }

    @Test(priority = 3)
    //Verify that user is not able to login with invalid username and invalid Password.
    public void invalidUsernameWithInvalidPassword() {
        loginPage.enterInvalidCredentials("Admin1","admin1234");
        Assert.assertEquals(loginPage.errorMessage(),"Invalid credentials");
    }

    @Test (priority = 4)
    //Verify that validation message gets displayed in case user leaves Username field as blank.
    public void emptyUsername() {
        loginPage.enterInvalidCredentials("","admin123");
        Assert.assertEquals(loginPage.errorMessage(),"Username cannot be empty");
    }

    @Test (priority = 5)
    //Verify that validation message gets displayed in case user leaves Username field as blank.
    public void emptyPassword() {
        loginPage.enterInvalidCredentials("Admin","");
        Assert.assertEquals(loginPage.errorMessage(),"Password cannot be empty");
    }

}
