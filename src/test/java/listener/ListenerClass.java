package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ListenerClass implements ITestListener {

    static  ExtentReports extentReports;
    static  ThreadLocal<ExtentTest> test=new ThreadLocal<>();

    @Override
    public void onTestSuccess(ITestResult result) {
       test.set(extentReports.createTest(result.getName()));
       test.get().log(Status.PASS, result.getName() + " Passed");
       test.get().addScreenCaptureFromBase64String(Utility.screenshotCapture(),"PassedScreenshot");
       test.remove();
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.set(extentReports.createTest(result.getName()));
        test.get().log(Status.FAIL,result.getThrowable());
        test.get().addScreenCaptureFromBase64String(Utility.screenshotCapture(),"FailedScreenshot");
        test.remove();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        if(extentReports==null) {
            extentReports = new ExtentReports();
            ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "-reports.html");
            extentReports.attachReporter(reporter);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
       // Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            extentReports.flush();
      //  }));

    }
}
