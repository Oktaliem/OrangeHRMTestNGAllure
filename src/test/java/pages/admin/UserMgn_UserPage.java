package pages.admin;

import com.ohrm.utilities.Log;
import com.testautomationguru.ocular.snapshot.Snap;
import features.Preparation;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;

@Snap("RichFace.png")
public class UserMgn_UserPage extends Preparation {

    WebDriver driver;

    //General purpose
    @FindBy(xpath = "//*[@id=\"systemUser-information\"]/a[1]")
    WebElement systemUsersToggle;
    @FindBy(id = "searchBtn")
    WebElement searchButtonSU;
    @FindBy(id = "searchSystemUser_userName")
    WebElement searchSystemUsers_userName;
    @FindBy(id = "searchSystemUser_userType")
    WebElement searchSystemUsers_userType;
    @FindBy(id = "searchSystemUser_employeeName_empName")
    WebElement searchSystemUsers_eName;
    @FindBy(id = "searchSystemUser_status")
    WebElement searchSystemUser_status;
    @FindBy(id = "btnAdd")
    WebElement addUserBtn;
    @FindBy(linkText = "Admin")
    WebElement adminLink;
    @FindBy(linkText = "User Management")
    WebElement userMagmtLink;

    By userLink = By.linkText("Users");
    By userAdmin = By.linkText("Admin");
    By userManagement = By.linkText("User Management");

    //Add New User
    @FindBy(id = "systemUser_userType")
    WebElement addSystemUsers_userName;
    @FindBy(id = "systemUser_employeeName_empName")
    WebElement addEmpName;
    @FindBy(id = "systemUser_userName")
    WebElement addUserName;
    @FindBy(id = "systemUser_status")
    WebElement add_status;
    @FindBy(id = "systemUser_password")
    WebElement addPassword;
    @FindBy(id = "systemUser_confirmPassword")
    WebElement addConfirmPassword;

    //Edit user
    @FindBy(id = "systemUser_employeeName_empName")
    WebElement employeeName;
    @FindBy(id = "systemUser_userName")
    WebElement userName;


    public UserMgn_UserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String checkSystemUserFormTitle() {
        return systemUsersToggle.getText();
    }

    public void clickOnSearchButtonSU() {
        searchButtonSU.click();
    }

    public void inputUserName(String userName) {
        searchSystemUsers_userName.sendKeys(userName);
    }

    public void selectUserRole(String userRole) {
        new Select(searchSystemUsers_userType).selectByVisibleText(userRole);
        //or
        //new Select(MyDrivers.findElement(By.id("searchSystemUser_userType"))).selectByVisibleText(userRole);
    }

    public void inputEmployeeName(String eName) {
        searchSystemUsers_eName.sendKeys(eName);
        searchSystemUsers_eName.sendKeys(Keys.TAB);
    }

    public void selectStatus(String userStatus) {
        new Select(searchSystemUser_status).selectByVisibleText(userStatus);
    }

    public void clickOnAddButtonTable() {
        addUserBtn.click();
    }

    public void selectAddUserRole(String userRole) {
        new Select(addSystemUsers_userName).selectByVisibleText(userRole);
    }

    public void inputAddEmployeeName(String eName) {
        addEmpName.sendKeys(eName);
    }

    public void inputAddUserName(String Uname) {
        addUserName.sendKeys(Uname);
    }

    public void selectAddStatus(String addStatus) {
        new Select(add_status).selectByVisibleText(addStatus);
    }

    public void inputAddPassword(String addPass) {
        addPassword.sendKeys(addPass);
    }

    public void inputAddConfirmPassword(String addConfirmPass) {
        addConfirmPassword.sendKeys(addConfirmPass);
    }

    public String getEmployeeName() {
        return employeeName.getAttribute("value");
    }

    public String getUserName() {
        return userName.getAttribute("value");
    }

    public void searchUserNameByEmployeeName(String fullName) {
        searchSystemUsers_eName.sendKeys(fullName);
        searchSystemUsers_eName.sendKeys(Keys.TAB);
        searchButtonSU.click();
    }

    public String getFirstUserNameInTheTable() {
        return driver.findElement(By.cssSelector("table#resultTable > tbody > tr > td:nth-of-type(2) > a")).getText();
    }

    public String getUserNameFromList(int i) {
        return driver.findElement(By.cssSelector("table#resultTable > tbody > tr:nth-of-type(" + i + ") > td:nth-of-type(2) > a")).getText();
    }

    @Step("When - User Search by Name")
    public void searchUserByUserName(String userName) {
        //http://www.avajava.com/tutorials/lessons/how-do-i-use-the-break-and-continue-statements.html
        //https://stackoverflow.com/questions/40819808/printing-table-to-the-console-with-selenium-java
        //http://seleniumwebdriver.org/print-table-data-using-selenium-webdriver
        WebElement table = driver.findElement(By.id("resultTable"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        here:
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("a"));
            for (WebElement col : cols) {
                System.out.print(col.getText());
                if (userName.trim().equals(col.getText().trim())) {
                    break here;
                }
            }
            System.out.println();
        }
        Log.info("Expected User Name: " + userName);
        searchSystemUsers_userName.sendKeys(userName);
        searchSystemUsers_eName.sendKeys(Keys.TAB);
        clickOnSearchButtonSU();
    }

    @Step("User Goes To User Form")
    public void userGoToUsersForm(){
        /**
         *logicnya adalah (berfungsi seperti mouse) click MENU admin akan keluar pilihan link User Management
         *mouse click ke link User Management akan muncul link User
         *mouse geser ke link User kemudian klik link tersebut
         */
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            driver.findElement(userAdmin).click();
            driver.findElement(userManagement).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(userLink)); // tunggu hingga element userLink muncul
            action.moveToElement(driver.findElement(userLink)).click().build().perform(); //geser mouse ke element userlink kemudian click link tsb
        }catch (NoSuchElementException e){
            Log.error("Cannot locating web element");
            fail();
        }
    }

    @Step("Then - User name is in the table list")
    public void verifyUserNameIsInTheTableList(String userName) {
        String userTable = driver.findElement(By.xpath("//A[@href='saveSystemUser?userId=1'][text()='"+userName+"']")).getText();
        assertEquals(userTable,userName);
    }

    @Step("Then - User name is NOT in the table list")
    public void verifyUserNameIsNotInTheTableList(String randomName) {
        String userTable = driver.findElement(By.xpath("//TD[@colspan='5'][text()='No Records Found']")).getText();
        assertEquals(userTable,"No Records Found");
    }
}
