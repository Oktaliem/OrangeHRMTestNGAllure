package features;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;

import java.io.IOException;

@Listeners({TestListener.class,UniversalVideoListener.class})
@Epic("Regression Tests")
@Feature("Login To Orange HRM Portal")
public class LoginTest extends Preparation {

    @Video
    @Test(priority = 0, description = "TC01 Login with empty user name and empty password")
    public void TC01_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("");
        loginPage.inputPassword("");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Username cannot be empty");
    }

    @Video
    @Test(priority = 0, description = "TC02 Login with empty user name and correct password")
    public void TC02_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("");
        loginPage.inputPassword("admin");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Username cannot be empty");
    }

    @Video
    @Test(priority = 0, description = "TC03 Login with correct password and empty password")
    public void TC03_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("admin");
        loginPage.inputPassword("");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Password cannot be empty");
    }

    @Video
    @Test(priority = 1, description = "TC04 Login with correct username and correct password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with correct username and correct password.")
    @Story("username and password login test")
    public void TC04_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("admin");
        loginPage.inputPassword("wrong password");
        loginPage.submitLogin();
        loginPage.landingToDashboardPage();
    }

    @Video
    @Test(priority = 0, description = "TC05 Login with incorrect user name and incorrect password")
    public void TC05_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("1234");
        loginPage.inputPassword("affff");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Invalid credentials");
    }

    @Video
    @Test(priority = 0, description = "TC06 Login with incorrect user name and correct password")
    public void TC06_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("1234");
        loginPage.inputPassword("admin");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Invalid credentials");
    }

    @Video
    @Test(priority = 0, description = "TC07 Login with correct user name and incorrect password")
    public void TC07_login() {
        userIsLandingToPage("Login");
        loginPage.inputUserName("admin");
        loginPage.inputPassword("$%^&*");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Invalid credentials");
    }

    @Video
    @Test(priority = 0, description = "TC08 Login with incorrect user name and incorrect password")
    public void TC08_login() throws IOException {
        userIsLandingToPage("Login");
        loginPage.inputUserName("derseeee");
        loginPage.inputPassword("$%^&*");
        loginPage.submitLogin();
        loginPage.verifyLoginErrorMessage("Invalid credentials");
    }


}
