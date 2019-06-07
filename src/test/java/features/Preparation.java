package features;

import com.ohrm.utilities.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.admin.UserMgn_UserPage;
import pages.login.LoginPage;
import utils.CreateRandomName;
import utils.Listeners.TestListener;
import utils.ScreenShootComparison;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.ohrm.utilities.OrangeHRMURL.LOGIN_URL;
import static com.ohrm.webdriver.MyDrivers.CHROMEDRIVER_PATH;
import static com.ohrm.webdriver.MyDrivers.CHROME_BROWSER;

public class Preparation {

    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public UserMgn_UserPage adminPage;
    public CreateRandomName name;
    public ScreenShootComparison image;
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void beforeMethodSetup() throws IOException {
        System.setProperty(CHROME_BROWSER, CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        adminPage = new UserMgn_UserPage(driver);
        name = new CreateRandomName();
        image = new ScreenShootComparison();
        Log.info("======================================================================================================");
        Log.info("=========================================TEST STARTED ================================================");
        Log.info("======================================================================================================");
        driver.manage().window().fullscreen();
        driver.get(LOGIN_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Step("Given - User is landing to expected page - {0}")
    public void userIsLandingToPage(String page) {
        if (page.equals("Login")) {
            TestListener.saveScreenshotPNG(driver);
        } else if (page.equals("User Management")) {
            loginPage.loginToOHRM("admin","admin");
            adminPage.userGoToUsersForm();
        }else if(page.equals("home")){
            loginPage.loginToOHRM("admin","admin");
        }
    }

    @AfterMethod
    public void closeBrowser() throws IOException {
        driver.quit();
        Log.info("======================================================================================================");
        Log.info("=========================================TEST FINISHED ================================================");
        Log.info("======================================================================================================");
    }
}