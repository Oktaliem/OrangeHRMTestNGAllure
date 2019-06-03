package pages.login;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Listeners.TestListener;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "txtUsername")
    WebElement userNameLoginInput;

    @FindBy(id = "txtPassword")
    WebElement userPasswordLoginInput;

    @FindBy(id = "btnLogin")
    WebElement loginSubmitBtn;

    @FindBy(xpath = "//SPAN[@id='spanMessage']")
    WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("When - User inputs user name")
    public void inputUserName(String login) {
        userNameLoginInput.sendKeys(login);
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("And - User inputs password")
    public void inputPassword(String pswd) {
        userPasswordLoginInput.sendKeys(pswd);
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("And - User submits login form")
    public void submitLogin() {
        loginSubmitBtn.click();
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("Then - login's error message will be displayed")
    public void verifyLoginErrorMessage(String error) {
        assertThat(loginErrorMessage.getText(), containsString(error));
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("Then - User is landing to dashboard page successfully")
    public void landingToDashboardPage() {
        assertThat(driver.getCurrentUrl(), containsString("http://127.0.0.1/orangehrm-4.0/symfony/web/index.php/dashboard"));
        TestListener.saveScreenshotPNG(driver);
    }

    @Step("User logs in to Portal")
    public void loginToOHRM(String name, String pasword) {
        userNameLoginInput.sendKeys(name);
        userPasswordLoginInput.sendKeys(pasword);
        loginSubmitBtn.click();
        TestListener.saveScreenshotPNG(driver);
    }
}

