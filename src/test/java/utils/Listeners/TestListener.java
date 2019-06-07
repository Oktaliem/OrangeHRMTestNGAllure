package utils.Listeners;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.ohrm.utilities.Log;
import features.Preparation;
import io.qameta.allure.Attachment;
import org.awaitility.Awaitility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    static byte[] attachVideo(String directory) throws IOException {
        await().atMost(5, SECONDS); // wait until last video is ready in the folder
        System.out.println("Informasi isFile: "+ VideoRecorder.getLastRecording().isFile());
        System.out.println("Informasi getAbsolutePath: "+ VideoRecorder.getLastRecording().getAbsolutePath());
        System.out.println("Informasi canRead: "+ VideoRecorder.getLastRecording().canRead());
        System.out.println("Informasi canWrite: "+ VideoRecorder.getLastRecording().canWrite());
        System.out.println("Informasi exists: "+ VideoRecorder.getLastRecording().exists());
/*
        try {
            byte[] video = new byte[0];
            breakForLoop:
            for (int i = 1; i < 5; i++) {
                System.out.println("Trial: " + i);
                if (VideoRecorder.getLastRecording().exists()) {
                    if (VideoRecorder.getLastRecording().canRead()) {
                        video = toByteArray(VideoRecorder.getLastRecording());
                    }
                    await().atMost(5, SECONDS); // wait until last video is ready in the folder
                    break breakForLoop;
                }
            }
            return video;
        }catch (IOException e){
            System.out.println("Unable to attach video......");
            return new byte[0];
        }finally {
            VideoRecorder.getLastRecording().delete();
        }
/*
        // try to attach video 3x
        if (VideoRecorder.getLastRecording().exists()){
            return toByteArray(VideoRecorder.getLastRecording());
        }else{
            try {
                await().atMost(5, SECONDS); // wait until last video is ready in the folder
                System.out.println("Unable to attach video, retry attaching the video......");
                File dir = new File(System.getProperty("user.dir") + "\\" + directory);
                File[] files = dir.listFiles();
                if (files == null || files.length == 0) {
                    System.out.println("No files in the directory");
                    return null;
                }
                File lastModifiedFile = files[0];
                for (int i = 1; i < files.length; i++) {
                    if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                        lastModifiedFile = files[i];
                    }
                }
                System.out.println("Last modified video name: " + lastModifiedFile);
                return toByteArray(lastModifiedFile);
            }catch (IOException e){
                try { return toByteArray(VideoRecorder.getLastRecording()); } catch (IOException a) { a.printStackTrace();return new byte[0]; }
            }finally {
                VideoRecorder.getLastRecording().delete();
            }
        }
*/

        try {
            return toByteArray(VideoRecorder.getLastRecording());
        } catch (IOException e) {
            System.out.println("Unable to attach video, retry attaching the video......");
            File dir = new File(System.getProperty("user.dir") + "\\" + directory);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("No files in the directory");
                return null;
            }
            File lastModifiedFile = files[0];
            for (int i = 1; i < files.length; i++) {
                if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                    lastModifiedFile = files[i];
                }
            }
            System.out.println("Last modified video name: " + lastModifiedFile);
            return toByteArray(lastModifiedFile);
        }finally {
            VideoRecorder.getLastRecording().delete();
        }

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
        Awaitility.setDefaultPollInterval(4000, TimeUnit.MILLISECONDS); // wait until last video is ready in the folder
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
            try { attachVideo("video"); } catch (IOException e) { e.printStackTrace();}
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        String folder = System.getProperty("user.dir");
        saveTextLog(getTestMethodName(iTestResult) + " failed and video taken! If video attachments are broken or incorrect "+
                "then check the second video attachment");
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
