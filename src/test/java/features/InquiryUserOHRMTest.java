package features;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;


@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Inquiry User Name")
public class InquiryUserOHRMTest extends BaseTest{

    /**
     * This testing purpose is to find User from User Management Page
     */

    @Test(priority = 0, description = "User is inquiry with valid name")
    public void TC01_inquiry(){
        userIsLandingToPage("Login");
        adminPage.searchUserByUserName("admin");
        adminPage.verifyUserNameIsInTheTableList("admin");
    }

    @Test(priority = 0, description = "User is inquiry with invalid name")
    public void TC02_inquiry(){
        userIsLandingToPage("User Management");
        String randomName = name.randomIdentifier();
        adminPage.searchUserByUserName(randomName);
        adminPage.verifyUserNameIsNotInTheTableList(randomName);
    }

}
