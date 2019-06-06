package pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasePage {

    public void takeFullPageScreenShootAndSave(WebDriver driver, String fileName){
        /* this screenshot will be saved in {project name folder}\screenshots\{fileName}. Full page screen shot with Shutterbug more reliable rather than aShot */
        Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,true).withName(fileName).save();
    }

    public void imageComparisonWithShutterbug(WebDriver driver, String base) throws IOException {
        File image = new File(base);
        BufferedImage expectedImage = ImageIO.read(image);
        boolean status = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 500, true).equals(expectedImage, 0.1);
        Assert.assertTrue(status);
    }

    public void imageComparisonWithAshot(String base, String actual, String result) throws IOException{
        BufferedImage expImage;
        BufferedImage actImage;
        ImageDiffer imgDiff;
        ImageDiff diff;
        BufferedImage markedImage;
        expImage = ImageIO.read(new File(System.getProperty("user.dir") + base));
        actImage = ImageIO.read(new File(System.getProperty("user.dir") + actual));
        imgDiff = new ImageDiffer();
        diff = imgDiff.makeDiff(actImage, expImage);
        if (diff.hasDiff() == true) {
            markedImage = diff.getMarkedImage();
            ImageIO.write(markedImage, "PNG", new File(System.getProperty("user.dir") + result)); }
        Assert.assertFalse(diff.hasDiff(), "Images are identical");
    }
}
