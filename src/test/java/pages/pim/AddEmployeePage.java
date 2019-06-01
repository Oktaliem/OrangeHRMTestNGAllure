package pages.pim;

import com.ohrm.model.TestDataModelPIM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class AddEmployeePage {

    WebDriver driver;

    @FindBy(id = "firstName")
    public WebElement fistNameField;
    @FindBy(id = "middleName")
    public WebElement middleNameField;
    @FindBy(id = "lastName")
    public WebElement lastNameField;
    @FindBy(id = "photofile")
    public WebElement photoField;
    @FindBy(id = "chkLogin")
    public WebElement CLDRadioButton;
    @FindBy(id = "btnSave")
    public WebElement buttonSave;
    @FindBy(id = "user_name")
    public WebElement userNameField;
    @FindBy(id = "user_password")
    public WebElement userPassField;
    @FindBy(id = "re_password")
    public WebElement userRePassField;
    @FindBy(css = "form#frmAddEmp li:nth-child(1) > span")
    public WebElement fistNameValidation;
    @FindBy(css = "form#frmAddEmp li:nth-child(3) > span")
    public WebElement lastNameValidation;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String firstName) {
        fistNameField.sendKeys(firstName);
    }

    public void inputMiddleName(String middleName) {
        middleNameField.sendKeys(middleName);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void uploadPhoto() {
        photoField.sendKeys("C:\\Users\\User\\Documents\\photo_2018-08-29_13-14-52.jpg");
    }

    public void clickOnRadioButton() {
        CLDRadioButton.click();
        await().atMost(1, SECONDS);
    }

    public void clickOnSaveButtonEmployeePage() {
        buttonSave.click();
    }

    public void inputUsernameAndPassword(TestDataModelPIM testData) {
        userNameField.sendKeys(testData.getUserName());
        userPassField.sendKeys(testData.getPassword());
        userRePassField.sendKeys(testData.getConfPassword());
    }

    public void goToSystemUserPage(String viewSystemUsers) {
        driver.navigate().to(viewSystemUsers);
    }

    public String getFieldValidationErrorMessage(String field) {
        if (field.equals("First Name")) {
            return fistNameValidation.getText();
        } else {
            return lastNameValidation.getText();
        }
    }
}
