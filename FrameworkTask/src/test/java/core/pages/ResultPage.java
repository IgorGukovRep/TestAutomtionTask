package core.pages;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static controller.config.DictionaryFileManager.readDataFromJson;
import static core.pages.ResultPage.ElementTitles.*;

public class ResultPage {

    private final String menuItemsLocatorsLabels = "//*[contains(@class,'hdtb-mitem')][contains(.,'%s')]";
    private final String toolLocatorLabel = "//*[contains(@id,'hdtb-tls')][contains(.,'%s')]";
    private final String resultLinkName = "//div[@id='search']//div[@class='g']//h3";
    private final String logoLabel = "//div[@class='logo']";

    public enum ElementTitles {

        SEARCH(readDataFromJson("res_Search")),
        ALL(readDataFromJson("res_All")),
        VIDEOS(readDataFromJson("res_Videos")),
        IMAGES(readDataFromJson("res_Images")),
        NEWS(readDataFromJson("res_News")),
        MAPS(readDataFromJson("res_Maps")),
        MORE(readDataFromJson("res_More")),
        SETTINGS(readDataFromJson("res_Settings")),
        TOOLS(readDataFromJson("res_Tools"));

        @Getter
        private String title;

        ElementTitles(String title) {
            this.title = title;
        }
    }

    public MainPage clickLogo() {
        $(By.xpath(logoLabel)).click();
        $(By.xpath(logoLabel)).shouldBe(Condition.disappear);
        return new MainPage();
    }

    public void checkResultPageIsLoaded() {
        $(By.xpath(String.format(menuItemsLocatorsLabels, ALL.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, VIDEOS.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, IMAGES.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, NEWS.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, MAPS.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, SETTINGS.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, MORE.getTitle()))).shouldBe(Condition.visible);
        $(By.xpath(String.format(toolLocatorLabel, TOOLS.getTitle()))).shouldBe(Condition.visible);
    }

    public String getResultLinkName(int lineNumber) {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("Wrong argument. Line Number - cannot be null");
        }
        return $$(By.xpath(resultLinkName)).get(--lineNumber).text();
    }

    public boolean isResultLinksDisappeared() {
        return $$(By.xpath(resultLinkName)).isEmpty();
    }
}
