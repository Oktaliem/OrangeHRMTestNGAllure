package pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public void takeFullPageScreenShootAndSave(WebDriver driver, String fileName){
        // this screenshot will be save in {project name folder}\screenshots\{fileName}
        // full page screen shot with Shutterbug, hasilnya akurat
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName(fileName).save();
    }
}
