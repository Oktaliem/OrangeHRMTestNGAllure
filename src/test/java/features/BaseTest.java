package features;

import com.ohrm.utils.CreateRandomName;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.admin.UserMgn_UserPage;
import pages.login.LoginPage;

import java.util.concurrent.TimeUnit;

import static com.ohrm.utils.OrangeHRMURL.LOGIN_URL;
import static com.ohrm.webdriver.driver.CHROMEDRIVER_PATH;
import static com.ohrm.webdriver.driver.CHROME_BROWSER;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public UserMgn_UserPage adminPage;
    public CreateRandomName name;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void beforeMethodSetup() {
        System.setProperty(CHROME_BROWSER, CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        adminPage = new UserMgn_UserPage(driver);
        name = new CreateRandomName();
    }

    @Step("Given - User is landing to expected page - {0}")
    public void userIsLandingToPage(String page) {
        driver.manage().window().fullscreen();
        driver.get(LOGIN_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (page.equals("Login")) {
            //
        } else if (page.equals("User Management")) {
            loginPage.loginToOHRM("admin","admin");
            adminPage.userGoToUsersForm();
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}