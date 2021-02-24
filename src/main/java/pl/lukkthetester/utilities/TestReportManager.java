package pl.lukkthetester.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestReportManager {

    private static final String path = "src/main/resources/extent-config.xml";

    public static Map extentTestMap = new HashMap();
    private static ExtentReports extent = ReportManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {

        extent.loadConfig((new File(path)));
        ExtentTest test = extent.startTest(testName, desc);

        extentTestMap.put((int) Thread.currentThread().getId(), test);

        return test;
    }
}
