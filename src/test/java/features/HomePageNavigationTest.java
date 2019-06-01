package features;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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
    @Test(priority = 0, description = "TC01 Able to navigate to admin page")
    public void TC01_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleAdmin();
        homePage.verifyLandingToCorrectPage(ADMIN_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC02 Able to navigate to PIM page")
    public void TC02_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModulePIM();
        homePage.verifyLandingToCorrectPage(PIM_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC03 Able to navigate to leave page")
    public void TC03_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleLeave();
        homePage.verifyLandingToCorrectPage(LEAVE_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC04 Able to navigate to time page")
    public void TC04_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleTime();
        homePage.verifyLandingToCorrectPage(TIME_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC05 Able to navigate to recruitment page")
    public void TC05_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleRecruitment();
        homePage.verifyLandingToCorrectPage(RECRUITMENT_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC06 Able to navigate to performance page")
    public void TC06_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModulePerformance();
        homePage.verifyLandingToCorrectPage(PERFORMANCE_PAGE);

    }

    @Video
    @Test(priority = 0, description = "TC07 ble to navigate to dashboard page")
    public void TC07_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleDashboard();
        homePage.verifyLandingToCorrectPage(DASHBOARD_PAGE);
    }

    @Video
    @Test(priority = 0, description = "TC08 Able to navigate to directory page")
    public void TC08_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleDirectory();
        homePage.verifyLandingToCorrectPage(DIRECTORY_PAGE);

    }

    @Video
    @Test(priority = 0, description = "TC09 Able to navigate to About modal")
    public void TC09_home_navigation() {
        userIsLandingToPage("home");
        homePage.goToLinkWelcomeAdmin().clickLinkAbout();
        homePage.verifyModalIsDisplayedWithInformativeMessage();
    }

    @Video
    @Test(priority = 0, description = "TC10 Able to navigate to many page")
    public void TC10_home_navigation() throws IOException {
        userIsLandingToPage("home");
        homePage.goToModuleAdmin();
        homePage.goToModulePIM();
        homePage.goToModuleLeave();
        homePage.goToModuleRecruitment();
        homePage.goToModuleDashboard();
        homePage.goToModuleDirectory();
        homePage.verifyLandingToCorrectPage(ADMIN_PAGE);
    }

}

