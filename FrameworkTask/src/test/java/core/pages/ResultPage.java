package core.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static controller.config.DictionaryFileManager.readDataFromJson;

public class ResultPage {

    private final String menuItemsLocatorsLabels = "//*[contains(@class,'hdtb-mitem')][contains(.,'%s')]";
    private final String toolLocatorLabel = "//*[contains(@id,'hdtb-tls')][contains(.,'%s')]";
    private final String resultLinkName = "//div[@id='search']//div[@class='g']//h3";
    private final String logoLabel = "//div[@class='logo']";

    private String SEARCH = readDataFromJson("res_Search");
    private String ALL = readDataFromJson("res_All");
    private String VIDEOS = readDataFromJson("res_Videos");
    private String IMAGES = readDataFromJson("res_Images");
    private String NEWS = readDataFromJson("res_News");
    private String MAPS = readDataFromJson("res_Maps");
    private String MORE = readDataFromJson("res_More");
    private String SETTINGS = readDataFromJson("res_Settings");
    private String TOOLS = readDataFromJson("res_Tools");

    public MainPage clickLogo() {
        $(By.xpath(logoLabel)).click();
        $(By.xpath(logoLabel)).shouldBe(Condition.disappear);
        return new MainPage();
    }

    public void checkResultPageIsLoaded() {
        $(By.xpath(String.format(menuItemsLocatorsLabels, ALL))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, VIDEOS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, IMAGES))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, NEWS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, MAPS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, SETTINGS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLabels, MORE))).shouldBe(Condition.visible);
        $(By.xpath(String.format(toolLocatorLabel, TOOLS))).shouldBe(Condition.visible);
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
