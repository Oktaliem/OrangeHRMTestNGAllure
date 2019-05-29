package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.ohrm.utilities.OrangeHRMURL.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class HomePage {

    WebDriver driver;

    /*
    @FindBy(xpath = "//*[@id=\"menu_admin_viewAdminModule\"]/b[1]")
    public WebElement adminTab;

    @FindBy(id = "menu_admin_UserManagement")
    public WebElement userManagementTab;

    @FindBy(id = "menu_admin_viewSystemUsers")
    public WebElement userTab;

    @FindBy(xpath = "//*[@id=\"welcome-menu\"]/ul[1]/li[3]/a[1]")
    public WebElement logoutOption;

    @FindBy(id = "welcome")
    public WebElement welcomeButton;

    @FindBy(css = "#menu_pim_viewPimModule > b")
    public WebElement PIMTab;

    @FindBy(xpath = "//*[@id=\"menu_pim_addEmployee\"]")
    public WebElement AddEmplyTab;
*/
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
        //PageFactory.initElements(driver, this);
    }

    @Step("When - User goes to Admin Module")
    public void goToModuleAdmin() {
        driver.findElement(menuAdminView).click();
    }

    @Step("When - User goes to PIM Module")
    public void goToModulePIM() {
        driver.findElement(menuPIMView).click();
    }

    @Step("When - User goes to Leave Module")
    public void goToModuleLeave() {
        driver.findElement(menuLeaveView).click();
    }

    @Step("When - User goes to Time Module")
    public void goToModuleTime() {
        driver.findElement(menuTime).click();
    }

    @Step("When - User goes to Recruitment Module")
    public void goToModuleRecruitment() {
        driver.findElement(menuRecruitment).click();
    }

    @Step("When - User goes to Performance Module")
    public void goToModulePerformance() {
        driver.findElement(menuPerformance).click();
    }

    @Step("When - User goes to Dashboard Module")
    public void goToModuleDashboard() {
        driver.findElement(menuDashboard).click();
    }

    @Step("When - User goes to Directory Module")
    public void goToModuleDirectory() {
        driver.findElement(menuDirectory).click();
    }

    @Step("When - User goes to Welcome Admin Module")
    public HomePage goToLinkWelcomeAdmin() {
        driver.findElement(menuLinkWelcomeAdmin).click();
        return this;
    }

/*
    public void waitUntilElementIsPresentByXpath(String element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }
*/
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
    public void verifyLandingToCorrectPage(String page) {
        assertThat(driver.getCurrentUrl(), containsString(page));
        switch (page) {
            case ADMIN_PAGE:
                assertThat(driver.findElement(By.id("systemUser-information")).getText(), containsString(getUserInfo()));
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
