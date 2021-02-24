package pl.lukkthetester.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    private final static By searchBox = By.id("search-text");
    private final static By cityInpt = By.id("cityField");
    private final static By searchBtn = By.id("search-submit");
    private final static By autoSuggestList = By.id("autosuggest-geo-ul");
    private final static By adsLinks = By.xpath("//h3/a");
    private final static By adsTitlesTxt = By.xpath("//h3/a/strong");
    private final static By adsCitiesTxt = By.xpath("//div/p/small[1]/span");

    public List<String> getTextFromAdvertisementsTitles() {

        $$(adsTitlesTxt).first().shouldBe(Condition.visible);
        return $$(adsTitlesTxt).texts();
    }

    public List<String> getTextFromAdvertisementsCities() {

        $$(adsCitiesTxt).last().shouldBe(Condition.visible);
        return $$(adsCitiesTxt).texts();
    }

    public SearchResultsPage putPhraseToSearchBox(String phrase) {

        $(searchBox).shouldBe(Condition.visible)
                .setValue(phrase);
        return this;
    }

    public SearchResultsPage selectCityFromAutoSuggestList(String city) {

        $(cityInpt).shouldBe(Condition.visible).setValue(city);
        $$(autoSuggestList).find(Condition.text(city)).click();
        return this;
    }

    public SearchResultsPage clickOnSearchBtn() {

        $(searchBtn).shouldBe(Condition.visible).click();
        return this;
    }

    public AdvertisementPage clickOnFirstAdLink() {

        $(adsLinks).shouldBe(Condition.visible);
        $$(adsLinks).first().click();
        return new AdvertisementPage();
    }
}
