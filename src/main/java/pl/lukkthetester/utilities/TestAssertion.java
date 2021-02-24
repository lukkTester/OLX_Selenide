package pl.lukkthetester.utilities;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.screenshot;

public class TestAssertion extends SoftAssert {

    private static int failures = 0;

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {

        TestReportManager.getTest().log(LogStatus.FAIL, ex.getMessage());
        TestReportManager.getTest().log(LogStatus.FAIL, TestReportManager.getTest()
                .addScreenCapture(screenshot("assertion_failed_" + failures)));
        failures++;
    }
}
