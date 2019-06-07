package utils.Listeners;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.ohrm.utilities.Log;
import features.Preparation;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static com.google.common.io.Files.toByteArray;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public class TestListener extends Preparation implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Attachment(value = "Video record", type = "video/avi")
    static byte[] attachVideo(String methodName) throws IOException {
        System.out.println("Informasi isFile: "+ VideoRecorder.getLastRecording().isFile());
        System.out.println("Informasi getAbsolutePath: "+ VideoRecorder.getLastRecording().getAbsolutePath());
        System.out.println("Informasi canRead: "+ VideoRecorder.getLastRecording().canRead());
        System.out.println("Informasi canWrite: "+ VideoRecorder.getLastRecording().canWrite());
        System.out.println("Informasi exists: "+ VideoRecorder.getLastRecording().exists());

        byte[] video = new byte[0];
        breakForLoop:
        for (int i = 1; i < 5; i++) {
            if (VideoRecorder.getLastRecording().getAbsolutePath().contains(methodName)){
                System.out.println("Trial " + i +" "+methodName);
                video = toByteArray(VideoRecorder.getLastRecording());
                System.out.println("Video attachment upload status ok" );
                break breakForLoop;
            }
            await().atMost(5, SECONDS);
        }
        return video;
    }

    @Attachment(value = "Visual test (screenshot from base image differ)", type = "image/png")
    public byte[] getScreenshotDiffer(Object path) {
        File expImage = new File(System.getProperty("user.dir") + path);
        try { return toByteArray(expImage); } catch (IOException e) { e.printStackTrace();return new byte[0]; }
    }

    @Attachment(value = "Base line image", type = "image/png")
    public byte[] getBaseLineImage(Object path) {
        File expImage = new File(System.getProperty("user.dir") + path);
        try { return toByteArray(expImage); } catch (IOException e) { e.printStackTrace();return new byte[0];}
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((Preparation) testClass).getDriver();
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
            if (getTestMethodName(iTestResult).equals(iTestResult.getTestContext().getAttribute("method"))) {
                getBaseLineImage(iTestResult.getTestContext().getAttribute("base"));
                getScreenshotDiffer(iTestResult.getTestContext().getAttribute("diff"));
            }
            System.out.println("Video captured for test case:" + getTestMethodName(iTestResult));
            try { attachVideo(getTestMethodName(iTestResult) ); } catch (IOException e) { e.printStackTrace();}
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        String folder = System.getProperty("user.dir");
        saveTextLog(getTestMethodName(iTestResult) + " failed and video taken! If video attachments are broken or incorrect "+
                "then check the second video attachment if exist or check your Jenkins Slave path "+ VideoRecorder.getLastRecording().getAbsolutePath());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }


}
