package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage{

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"menu_admin_viewAdminModule\"]/b[1]")
    //@FindBy(xpath = "//b[contains(text(),'Admin')]")
    public WebElement adminTab;

    @FindBy(id = "menu_admin_UserManagement")
    public WebElement userManagementTab;

    @FindBy(id = "menu_admin_viewSystemUsers")
    public WebElement userTab;

    @FindBy(xpath = "//*[@id=\"welcome-menu\"]/ul[1]/li[3]/a[1]")
    public WebElement logoutOption;

    @FindBy(id = "welcome")
    public WebElement welcomeButton;

    @FindBy(css="#menu_pim_viewPimModule > b")
    public WebElement PIMTab;

    @FindBy(xpath="//*[@id=\"menu_pim_addEmployee\"]")
    public WebElement AddEmplyTab;

    ////*[@id="menu_pim_addEmployee"]

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAdminModule() {
        waitUntilElementIsPresentByXpath("//*[@id=\"menu_admin_viewAdminModule\"]/b[1]");
        adminTab.click();
    }

    public void clickOnUserManagementList() {
        userManagementTab.click();
    }

    public void clickOnUserTab() {
        /*
        new Actions(driver).moveToElement(userTab).perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_admin_viewSystemUsers")));
        userTab.click();
        */
        driver.findElement(By.cssSelector("#menu_admin_viewSystemUsers")).click();
    }

    public void clickOnWelcomeButton() {
        welcomeButton.click();
    }

    public void clickOnLogoutOption() {
        waitUntilElementIsPresentByXpath("//*[@id=\"welcome-menu\"]/ul[1]/li[3]/a[1]");
        logoutOption.click();
    }

    public void waitUntilElementIsPresentByXpath(String element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public void clickOnPIMTab(){
        PIMTab.click();
    }

    public void clickOnAddEmployeeTab(){
        AddEmplyTab.click();
    }


}
