package pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ScreenShootComparison;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.ohrm.utilities.OrangeHRMURL.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class HomePage {

    WebDriver driver;
    ScreenShootComparison imageComparison;

    By menuAdminView = By.id("menu_admin_viewAdminModule");
    By menuPIMView = By.xpath("//B[text()='PIM']");
    By menuLeaveView = By.id("menu_leave_viewLeaveModule");
    By menuTime = By.linkText("Time");
    By menuRecruitment = By.linkText("Recruitment");
    By menuPerformance = By.linkText("Performance");
    By menuDashboard = By.linkText("Dashboard");
    By menuDirectory = By.linkText("Directory");
    By menuLinkWelcomeAdmin = By.linkText("Welcome Admin");
    By menuLinkAbout = By.linkText("About");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("When - User goes to Admin Module")
    public void goToModuleAdmin() {
        driver.findElement(menuAdminView).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("AdminPage").save();
    }

    @Step("When - User goes to PIM Module")
    public void goToModulePIM() {
        driver.findElement(menuPIMView).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("PIMPage").save();
    }

    @Step("When - User goes to Leave Module")
    public void goToModuleLeave() {
        driver.findElement(menuLeaveView).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("LeavePage").save();
    }

    @Step("When - User goes to Time Module")
    public void goToModuleTime() {
        driver.findElement(menuTime).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("TimePage").save();
    }

    @Step("When - User goes to Recruitment Module")
    public void goToModuleRecruitment() {
        driver.findElement(menuRecruitment).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("RecruitmentPage").save();
    }

    @Step("When - User goes to Performance Module")
    public void goToModulePerformance() {
        driver.findElement(menuPerformance).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("PerformacePage").save();
    }

    @Step("When - User goes to Dashboard Module")
    public void goToModuleDashboard() {
        driver.findElement(menuDashboard).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("DasghboardPage").save();
    }

    @Step("When - User goes to Directory Module")
    public void goToModuleDirectory() {
        driver.findElement(menuDirectory).click();
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName("DirectoryPage").save();
    }

    @Step("When - User goes to Welcome Admin Module")
    public HomePage goToLinkWelcomeAdmin() {
        driver.findElement(menuLinkWelcomeAdmin).click();
        return this;
    }

    public String getAboutInfo() {
        return "About";
    }

    public String getUserInfo() {
        return "System Users";
    }

    public String getEmployeeInfo() {
        return "Employee Information";
    }

    @Step("Then - User is Landing to Correct Page")
    public void verifyLandingToCorrectPage(String page) throws IOException {
        File image;
        BufferedImage expectedImage;
        boolean status;
        assertThat(driver.getCurrentUrl(), containsString(page));
        switch (page) {
            case ADMIN_PAGE:
                assertThat(driver.findElement(By.id("systemUser-information")).getText(), containsString(getUserInfo()));
                //===================image size comparison i.e 360 x234 , fail =========//
                image = new File(".\\BaseImages\\DirectoryPage.png");
                expectedImage = ImageIO.read(image);
                status = Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).equals(expectedImage,0.1);
                //status = Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).equalsWithDiff(expectedImage,".\\NewImages\\DirectoryPage.png",0.1);
                Assert.assertTrue(status);
                //===================image size comparison i.e 360 x234 , fail =========//
                break;
            case PIM_PAGE:
                assertThat(driver.findElement(By.id("employee-information")).getText(), containsString(getEmployeeInfo()));
                imageComparison.compareFullPageScreenShoot(".\\BaseImages\\PIMPage.png","ActualPIMPage");
                break;
            case LEAVE_PAGE:
                assertThat(driver.findElement(By.id("locationHeading")).getText(), containsString("Leave Period"));
                break;
            case DASHBOARD_PAGE:
                assertThat(driver.findElement(By.id("content")).getText(), containsString("Dashboard"));
                break;
            case DIRECTORY_PAGE:
                assertThat(driver.findElement(By.id("content")).getText(), containsString("Search Directory"));

                //===================image size comparison i.e 360 x234 , success =========//
                image = new File(".\\BaseImages\\DirectoryPage.png");
                expectedImage = ImageIO.read(image);
                status = Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).equals(expectedImage,0.1);
                Assert.assertTrue(status);
                //===================image size comparison i.e 360 x234 , success =========//


                break;
            case TIME_PAGE:
                assertThat(driver.findElement(By.id("defineTimesheet")).getText(), containsString("Define Timesheet Period"));
                break;
            case RECRUITMENT_PAGE:
                assertThat(driver.findElement(By.id("srchCandidates")).getText(), containsString("Candidates"));
                break;
        }
    }

    @Step("And - User clicks on About link")
    public void clickLinkAbout() {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(menuLinkAbout)); // tunggu hingga element userLink muncul
            action.moveToElement(driver.findElement(menuLinkAbout)).click().build().perform(); //geser mouse ke element userlink kemudian click link tsb
        } catch (NoSuchElementException e) {
            System.out.println("Cannot located web element");
        }

    }

    @Step("Then - Modal is Displayed with informative message")
    public void verifyModalIsDisplayedWithInformativeMessage() {
        assertThat(driver.findElement(By.id("displayAbout")).getText(), containsString(getAboutInfo()));
        assertThat(driver.findElement(By.id("companyInfo")).getText(), containsString("Company Name: Okta Jaya Pte Ltd"));
        System.out.println(driver.findElement(By.id("displayAbout")).getText());
    }
}
