package pl.lukkthetester.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdvertisementPage {

    private final static By phoneNoDiv = By.xpath("//ul[@id='contact_methods']/li/div");
    private final static By imageDiv = By.id("descImage");

    public String getTextFromPhoneNoDiv() {
        $(phoneNoDiv).shouldBe(Condition.visible);
        return $(phoneNoDiv).find("strong").getText();
    }

    public AdvertisementPage clickOnPhoneCover() {
        $(phoneNoDiv).click();
        return this;
    }

    public boolean checkPhotoPresence() {
        $(imageDiv).should(Condition.exist);
        return $(imageDiv).find("img").isImage();
    }
}
