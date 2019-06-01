package features;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;

@Listeners({TestListener.class,UniversalVideoListener.class})
@Epic("Regression Tests")
@Feature("Inquiry User Name")
public class InquiryUserOHRMTest extends Preparation {

    @Video
    @Test(priority = 1, description = "TC01 User is inquiry with valid name")
    public void TC01_inquiry(){
        userIsLandingToPage("Login");
        adminPage.searchUserByUserName("admin");
        adminPage.verifyUserNameIsInTheTableList("admin");
    }

    @Video
    @Test(priority = 0, description = "TC02 User is inquiry with invalid name")
    public void TC02_inquiry(){
        userIsLandingToPage("User Management");
        String randomName = name.randomIdentifier();
        adminPage.searchUserByUserName(randomName);
        adminPage.verifyUserNameIsNotInTheTableList(randomName);
    }

}
