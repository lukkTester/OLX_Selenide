package pl.lukkthetester.features;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.lukkthetester.BaseTest;
import pl.lukkthetester.pages.AdvertisementPage;
import pl.lukkthetester.pages.HomePage;
import pl.lukkthetester.pages.SearchResultsPage;

public class AdvertisementPageFeaturesTest extends BaseTest {

    private String item = "Samsung S10";

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    AdvertisementPage advertisementPage;

    @BeforeTest
    public void searchForItemAndOpen() {
        homePage = new HomePage();
        homePage.putPhraseToSearchInput(item);
        searchResultsPage = homePage.clickOnSearchButton();
        advertisementPage = searchResultsPage.clickOnFirstAdLink();
    }

    @Test
    public void verifyClickingOnReceiverButtonUnveilsOwnersPhoneNumber() {

        Assert.assertEquals(advertisementPage.getTextFromPhoneNoDiv(), "Zadzwoń");

        advertisementPage.clickOnPhoneCover();

        Assert.assertTrue(advertisementPage.getTextFromPhoneNoDiv().matches("[0-9]{3} [0-9]{3} [0-9]{3}"),
                "Phone number does not match pattern XXX XXX XXX");
    }
}
