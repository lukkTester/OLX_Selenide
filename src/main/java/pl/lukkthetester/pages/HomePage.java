package pl.lukkthetester.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private static final By favBtn = By.id("observed-search-link");
    private static final By searchBox = By.id("headerSearch");
    private static final By searchBtn = By.id("submit-searchmain");

    public FavouritesPage clickOnFavouritesButton() {
        $(favBtn).click();
        return new FavouritesPage();
    }

    public void putPhraseToSearchInput(String searchPhrase) {
        $(searchBox).setValue(searchPhrase);
    }

    public SearchResultsPage clickOnSearchButton() {
        $(searchBtn).click();
        return new SearchResultsPage();
    }
}
