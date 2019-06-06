package pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.Listeners.TestListener;
import utils.ScreenShootComparison;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.ohrm.utilities.OrangeHRMURL.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class HomePage extends BasePage{

    WebDriver driver;
    public ScreenShootComparison eye;

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
        takeFullPageScreenShootAndSave(driver, "AdminPage");
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("When - User goes to PIM Module")
    public void goToModulePIM() {
        driver.findElement(menuPIMView).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"PIMPage");
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);// full page screen shot with aShot, hasilnya tidak akurat
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\PIMPage_aShot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("When - User goes to Leave Module")
    public void goToModuleLeave() {
        driver.findElement(menuLeaveView).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"LeavePage");
    }

    @Step("When - User goes to Time Module")
    public void goToModuleTime() {
        driver.findElement(menuTime).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"TimePage");
    }

    @Step("When - User goes to Recruitment Module")
    public void goToModuleRecruitment() {
        driver.findElement(menuRecruitment).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"RecruitmentPage");
    }

    @Step("When - User goes to Performance Module")
    public void goToModulePerformance() {
        driver.findElement(menuPerformance).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"PerformancePage");
    }

    @Step("When - User goes to Dashboard Module")
    public void goToModuleDashboard(){
        driver.findElement(menuDashboard).click();
        TestListener.saveScreenshotPNG(driver);
        Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).withName("DashboardPage").save(); // hasilnya valid
        WebElement myWebElement = driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution > canvas:nth-of-type(2)"));

        //aShot
        Screenshot screenshot = new AShot().takeScreenshot(driver, myWebElement); //method bisa dipakai tp hasilnya tidak akurat kadang2;
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\dashboard_pie_chart_aShot.png"));
        } catch (IOException e) { e.printStackTrace(); }

        Screenshot screenshot2 = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, myWebElement); //method bisa dipakai tp hasilnya tidak akurat kadang2;
        try {
            ImageIO.write(screenshot2.getImage(), "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\dashboard_pie_chart_aShot_2.png"));
        } catch (IOException e) { e.printStackTrace(); }

        Screenshot screenshot3 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver, myWebElement); //method bisa dipakai tp hasilnya tidak akurat kadang2;
        try {
            ImageIO.write(screenshot3.getImage(), "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\dashboard_pie_chart_aShot_3.png"));
        } catch (IOException e) { e.printStackTrace(); }


        //Shutterbug
        Shutterbug.shootElement(driver, myWebElement).withName("dashboard_pie_chart").save(); //method bisa dipakai tp hasilnya tidak akurat
        Shutterbug.shootPage(driver).highlightWithText(myWebElement, "dashboard_pie_chart_2").withName("dashboard_pie_chart_2").save();//method bisa dipakai tp hasilnya tidak akurat
        Shutterbug.shootPage(driver).highlight(myWebElement).withName("dashboard_pie_chart_3").save();//method bisa dipakai tp hasilnya tidak akurat
    }

    @Step("When - User goes to Directory Module")
    public void goToModuleDirectory() {
        driver.findElement(menuDirectory).click();
        TestListener.saveScreenshotPNG(driver);
        takeFullPageScreenShootAndSave(driver,"DirectoryPage");
    }

    @Step("When - User goes to Welcome Admin Module")
    public HomePage goToLinkWelcomeAdmin() {
        driver.findElement(menuLinkWelcomeAdmin).click();
        TestListener.saveScreenshotPNG(driver);
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

    @Step("Then - User is Landing to Expected Page")
    public void verifyLandingToCorrectPage(String page) throws IOException {
        File image;
        BufferedImage expectedImage;
        boolean status;
        BufferedImage expImage;
        BufferedImage actImage;
        ImageDiffer imgDiff;
        ImageDiff diff;
        BufferedImage markedImage;

        assertThat(driver.getCurrentUrl(), containsString(page));
        switch (page) {
            case ADMIN_PAGE:
                assertThat(driver.findElement(By.id("systemUser-information")).getText(), containsString(getUserInfo()));
                //=================== Shutterbug, image dimension comparison i.e 360 x234 , fail =========//
                image = new File(".\\baseimages\\DirectoryPage.png");
                expectedImage = ImageIO.read(image);
                status = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).equals(expectedImage, 0.1);
                Assert.assertTrue(status);
                //=================== Shutterbug, image dimension comparison i.e 360 x234 , fail =========//
                break;
            case PIM_PAGE:
                assertThat(driver.findElement(By.id("employee-information")).getText(), containsString(getEmployeeInfo()));
                break;
            case LEAVE_PAGE:
                assertThat(driver.findElement(By.id("locationHeading")).getText(), containsString("Leave Period"));
                break;
            case DASHBOARD_PAGE:
                assertThat(driver.findElement(By.id("content")).getText(), containsString("Dashboard"));
                break;
            case DIRECTORY_PAGE:
                assertThat(driver.findElement(By.id("content")).getText(), containsString("Search Directory"));
                //===================Shutterbug, image size comparison i.e 360 x234 , success =========//
                image = new File(".\\baseimages\\DirectoryPage.png");
                expectedImage = ImageIO.read(image);
                status = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).equals(expectedImage, 0.1);
                Assert.assertTrue(status);
                //===================Shutterbug, image size comparison i.e 360 x234 , success =========//

                //=================== aShot, images comparison in success scenario ======================================================//
                expImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\baseimages\\DirectoryPage.png"));
                actImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\screenShots\\DirectoryPage.png"));
                imgDiff = new ImageDiffer();
                diff = imgDiff.makeDiff(actImage, expImage);
                if (diff.hasDiff() == true) {
                    markedImage = diff.getMarkedImage();
                    ImageIO.write(markedImage, "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\DirectoryPage_Diff.png"));
                }
                Assert.assertFalse(diff.hasDiff(), "Images are identical");
                //Result : aShot is able to compare image content
                //=================== aShot, images comparison in success scenario ======================================================//

                break;
            case TIME_PAGE:
                assertThat(driver.findElement(By.id("defineTimesheet")).getText(), containsString("Define Timesheet Period"));
                //=================== aShot, images comparison in success scenario ======================================================//
                expImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\baseimages\\TimePage.png"));
                actImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\screenShots\\TimePage.png"));
                imgDiff = new ImageDiffer();
                diff = imgDiff.makeDiff(actImage, expImage);
                Assert.assertFalse(diff.hasDiff(), "Images are identical");
                //=================== aShot, images comparison in success scenario ======================================================//
                break;
            case RECRUITMENT_PAGE:
                assertThat(driver.findElement(By.id("srchCandidates")).getText(), containsString("Candidates"));

                //===================Shutterbug, image dimension comparison i.e 360 x234 , fail =========//
                image = new File(".\\baseimages\\RecruitmentPageFalse.png");
                expectedImage = ImageIO.read(image);
                status = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).equals(expectedImage, 0.1);
                Assert.assertTrue(status);
                //Result : Shutterbug only able to compare screen dimension but unable to compare image content, fail scenario but result is passed
                //===================Shutterbug, image dimension comparison i.e 360 x234 , fail =========//

                //=================== aShot, images comparison in fail scenario ======================================================//
                expImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\baseimages\\RecruitmentPageFalse.png"));
                actImage = ImageIO.read(new File(System.getProperty("user.dir") + ".\\screenShots\\RecruitmentPage.png"));
                imgDiff = new ImageDiffer();
                diff = imgDiff.makeDiff(actImage, expImage);
                if (diff.hasDiff() == true) {
                    markedImage = diff.getMarkedImage();
                    ImageIO.write(markedImage, "PNG", new File(System.getProperty("user.dir") + ".\\screenshots\\RecruitmentPage_Diff.png"));
                }
                Assert.assertFalse(diff.hasDiff(), "Images are identical");
                //Result : aShot is able to compare image content
                //=================== aShot, images comparison in fail scenario ======================================================//
                break;
        }
        TestListener.saveScreenshotPNG(driver);
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
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("Then - Modal is Displayed with informative message")
    public void verifyModalIsDisplayedWithInformativeMessage() {
        assertThat(driver.findElement(By.id("displayAbout")).getText(), containsString(getAboutInfo()));
        assertThat(driver.findElement(By.id("companyInfo")).getText(), containsString("Company Name: Okta Jaya Pte Ltd"));
        TestListener.saveScreenshotPNG(driver);
    }
}
