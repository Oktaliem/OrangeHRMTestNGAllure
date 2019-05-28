package pages.pim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeListPage{

    WebDriver driver;

    @FindBy(id = "personal_txtEmpFirstName") public  WebElement fistNameField;
    @FindBy(id = "personal_txtEmpMiddleName") public WebElement middleNameField;
    @FindBy(id = "personal_txtEmpLastName") public WebElement lastNameField;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameInformation() {
        //https://www.seleniumeasy.com/selenium-tutorials/how-to-get-attribute-values-using-webdriver
        return fistNameField.getAttribute("value");
    }

    public String getMiddleNameInformation() {
        return middleNameField.getAttribute("value");
    }

    public String getLastNameInformation() {
        return lastNameField.getAttribute("value");
    }
}
