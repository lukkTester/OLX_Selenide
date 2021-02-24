package pl.lukkthetester.utilities;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extent;

    private static final String TEST_REPORT_NAME = "/Test-Results.html";
    private static final String outputFolder = System.getProperty("user.dir") + "/output";
    private static String folderPath;

    public static String createFolderStructure() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String month = new SimpleDateFormat(("MMMMMMMMM")).format((cal.getTime()));
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date dt = new Date();
        String date = dateFormat.format(dt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);
        folderPath = outputFolder + File.separator + year + File.separator + month + File.separator + date
                + File.separator + timestamp + File.separator;

        File files = new File(folderPath);
        if (!files.exists()) {
            new File(folderPath).mkdirs();
            return folderPath;
        } else {
            return folderPath;
        }
    }

    public static String getFolderPath() {
        return folderPath;
    }

    public synchronized static ExtentReports getReporter() {

        if (extent == null) {
            extent = new ExtentReports(getFolderPath() + TEST_REPORT_NAME);
        }
        return extent;
    }
}
