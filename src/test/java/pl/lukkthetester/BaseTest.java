package pl.lukkthetester;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.lukkthetester.utilities.ReportManager;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeSuite
    public void setup() {

        ReportManager.createFolderStructure();

        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.olx.pl/";
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = ReportManager.getFolderPath();
    }

    @BeforeMethod
    public void start() {
        open(Configuration.baseUrl);
    }
}
