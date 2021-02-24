package pl.lukkthetester.utilities;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestReportManager.getTest().log(LogStatus.PASS, "Test passed");
        TestReportManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestReportManager.getTest().log(LogStatus.FAIL, "Test failed");
        TestReportManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestReportManager.getTest().log(LogStatus.SKIP, "Test skipped");
        TestReportManager.endTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getReporter().flush();
        ReportManager.getReporter().close();
    }
}
