package features;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.im4java.core.IM4JavaException;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;

import java.io.IOException;

import static com.ohrm.utilities.OrangeHRMURL.*;

@Listeners({TestListener.class,UniversalVideoListener.class})
@Epic("Regression Tests")
@Feature("Home Page Navigation")
public class HomePageNavigationTest extends Preparation {

    @Video
    @Test(description = "TC01 Able to navigate to admin page - Visual Test")
    public void TC01_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleAdmin();
        homePage.verifyLandingToCorrectPage(ADMIN_PAGE, context);
    }

    @Video
    @Test(description = "TC02 Able to navigate to PIM page")
    public void TC02_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModulePIM();
        homePage.verifyLandingToCorrectPage(PIM_PAGE, context);
    }

    @Video
    @Test(description = "TC03 Able to navigate to leave page - Visual Test")
    public void TC03_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleLeave();
        homePage.verifyLandingToCorrectPage(LEAVE_PAGE, context);
    }

    @Video
    @Test(description = "TC04 Able to navigate to time page - Visual Test")
    public void TC04_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleTime();
        homePage.verifyLandingToCorrectPage(TIME_PAGE, context);
    }

    @Video
    @Test(description = "TC05 Able to navigate to recruitment page - Visual Test")
    public void TC05_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleRecruitment();
        homePage.verifyLandingToCorrectPage(RECRUITMENT_PAGE, context);
    }

    @Video
    @Test(description = "TC06 Able to navigate to performance page")
    public void TC06_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModulePerformance();
        homePage.verifyLandingToCorrectPage(PERFORMANCE_PAGE, context);
    }

    @Video
    @Test(description = "TC07 ble to navigate to dashboard page - Visual Test")
    public void TC07_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleDashboard();
        homePage.verifyLandingToCorrectPage(DASHBOARD_PAGE, context);
    }

    @Video
    @Test(description = "TC08 Able to navigate to directory page - Visual Test")
    public void TC08_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleDirectory();
        homePage.verifyLandingToCorrectPage(DIRECTORY_PAGE, context);

    }

    @Video
    @Test(description = "TC09 Able to navigate to About modal")
    public void TC09_home_navigation() {
        userIsLandingToPage("home");
        homePage.goToLinkWelcomeAdmin().clickLinkAbout();
        homePage.verifyModalIsDisplayedWithInformativeMessage();
    }

    @Video
    @Test(description = "TC10 Able to navigate to many page")
    public void TC10_home_navigation(ITestContext context) throws IOException, IM4JavaException, InterruptedException {
        userIsLandingToPage("home");
        homePage.goToModuleAdmin();
        homePage.goToModulePIM();
        homePage.goToModuleLeave();
        homePage.goToModuleRecruitment();
        homePage.goToModuleDashboard();
        homePage.goToModuleDirectory();
        homePage.verifyLandingToCorrectPage(ADMIN_PAGE, context);
    }

}

