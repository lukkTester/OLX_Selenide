package pl.lukkthetester.user_journey;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.lukkthetester.BaseTest;
import pl.lukkthetester.pages.AdvertisementPage;
import pl.lukkthetester.pages.HomePage;
import pl.lukkthetester.pages.SearchResultsPage;
import pl.lukkthetester.utilities.TestAssertion;
import pl.lukkthetester.utilities.TestListener;
import pl.lukkthetester.utilities.TestReportManager;

import static com.codeborne.selenide.Selenide.title;

@Listeners(TestListener.class)
public class SearchAdsTest extends BaseTest {

    TestAssertion assertion = new TestAssertion();

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    AdvertisementPage advertisementPage;

    @BeforeMethod
    public void goToSearchPage() {
        homePage = new HomePage();
        searchResultsPage = homePage.clickOnSearchButton();
    }

    @Test
    public void shouldFindAdvertisementsForSpecificMobileModel() {

        String item = "Samsung S8";
        String city = "Warszawa";
        String firstAdTitle;

        // scenario: Searching advertisements with detailed criteria
        // and user wants to search for <<item>> advertisements
        // and a seller should be from <<city>>
        // and advertisements should have photos
        TestReportManager.startTest("SEARCH-ITEMS", "Test verifies a capability of searching specific item");
        ExtentTest report = TestReportManager.getTest();

        // when user puts an item name to search box
        // and chooses a city
        // and checks "only with photo" checkbox
        // and clicks search button
        report.log(LogStatus.INFO, "User searches advertisements by city and photo presence");
        searchResultsPage.putPhraseToSearchBox(item);
        searchResultsPage.selectCityFromAutoSuggestList(city);
        searchResultsPage.clickOnSearchBtn();

        // then user sees search results page with a title contains <<item>> and <<city>>
        // and results ads titles contain <<item>>
        assertion.assertTrue(title().contains(item) && title().contains(city));

        searchResultsPage.getTextFromAdvertisementsTitles().stream().forEach(title ->
                assertion.assertTrue(title.contains("Samsung"), "Ads title does not contain 'Samsung' word."));

        searchResultsPage.getTextFromAdvertisementsCities().stream().forEach(sellerCity -> {
            assertion.assertTrue(sellerCity.contains(city), "Ads cities does not contain " + city + " word.");
        });

        firstAdTitle = searchResultsPage.getTextFromAdvertisementsTitles().get(0);

        // when user opens first advertisement
        report.log(LogStatus.INFO, "User opens first advertisement from the list");
        advertisementPage = searchResultsPage.clickOnFirstAdLink();

        // then the advertisement is open
        // and the advertisement has at least 1 image properly loaded
        assertion.assertTrue(title().contains(firstAdTitle), "Page title does not contain: " + firstAdTitle);
        assertion.assertTrue(advertisementPage.checkPhotoPresence(), "Photo is not displaying properly");

        assertion.assertAll();
    }
}
