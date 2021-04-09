package core.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static controller.config.DictionaryFileManager.readDataFromJson;

public class ResultPage extends BasePage {

    private final String menuItemsLocatorsLinks = "//*[contains(@class,'hdtb-mitem')][contains(.,'%s')]";
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
        logger.info("Click on Google lol");
        return new MainPage();
    }

    public void checkResultPageIsLoaded() {
        logger.debug(String.format("Startin to check: %s, %s, %s, %s, %s, %s, %s, %s",
                ALL, VIDEOS, IMAGES, NEWS, MAPS, SETTINGS, MORE, TOOLS));
        $(By.xpath(String.format(menuItemsLocatorsLinks, ALL))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, VIDEOS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, IMAGES))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, NEWS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, MAPS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, SETTINGS))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, MORE))).shouldBe(Condition.visible);
        $(By.xpath(String.format(toolLocatorLabel, TOOLS))).shouldBe(Condition.visible);
        logger.debug("All elements have been  checked");
    }

    public String getResultLinkName(int lineNumber) {
        logger.debug("Get result link");
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("Wrong argument. Line Number - cannot be null");
        }
        return $$(By.xpath(resultLinkName)).get(--lineNumber).text();

    }

    public boolean isResultLinksDisappeared() {
        return $$(By.xpath(resultLinkName)).isEmpty();
    }
}
