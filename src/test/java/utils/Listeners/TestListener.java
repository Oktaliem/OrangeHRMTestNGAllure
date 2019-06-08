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
        Log.info("Checking isFile: "+ VideoRecorder.getLastRecording().isFile());
        Log.info("Checking getAbsolutePath: "+ VideoRecorder.getLastRecording().getAbsolutePath());
        Log.info("Checking canRead: "+ VideoRecorder.getLastRecording().canRead());
        Log.info("Checking canWrite: "+ VideoRecorder.getLastRecording().canWrite());
        Log.info("Checking exists: "+ VideoRecorder.getLastRecording().exists());

        byte[] video = new byte[0];
        breakForLoop:
        for (int i = 1; i < 11; i++) {
            if (VideoRecorder.getLastRecording().getAbsolutePath().contains(methodName)){
                Log.info(" Attach Video -  Trial " + i +" "+ methodName);
                video = toByteArray(VideoRecorder.getLastRecording());
                Log.info("Video attachment upload status: OK" );
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
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((Preparation) testClass).getDriver();
        if (driver instanceof WebDriver) {
            Log.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
            saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
            if (getTestMethodName(iTestResult).equals(iTestResult.getTestContext().getAttribute("method"))) {
                getBaseLineImage(iTestResult.getTestContext().getAttribute("base"));
                getScreenshotDiffer(iTestResult.getTestContext().getAttribute("diff"));
            }
            Log.info("Video captured for test case:" + getTestMethodName(iTestResult));
            try { attachVideo(getTestMethodName(iTestResult) ); } catch (IOException e) { e.printStackTrace();}
            saveTextLog(getTestMethodName(iTestResult) + " failed and video taken! If video attachment is broken or incorrect "+
                    "then check the second video attachment OR check your Jenkins Slave path : "+ VideoRecorder.getLastRecording().getAbsolutePath());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }


}
