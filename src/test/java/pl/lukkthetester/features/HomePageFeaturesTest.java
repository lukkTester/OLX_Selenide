package pl.lukkthetester.features;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.lukkthetester.BaseTest;
import pl.lukkthetester.pages.HomePage;

import static com.codeborne.selenide.WebDriverRunner.url;

public class HomePageFeaturesTest extends BaseTest {

    HomePage homePage = new HomePage();

    @Test
    public void verifyFavouritesButtonRedirectsToFavouritesItems() {

        homePage.clickOnFavouritesButton();

        Assert.assertEquals(url(), "https://www.olx.pl/obserwowane/wyszukiwania/");
    }
}
