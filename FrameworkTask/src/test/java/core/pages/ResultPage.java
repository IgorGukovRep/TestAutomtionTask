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

    private String all = readDataFromJson("res_All");
    private String videos = readDataFromJson("res_Videos");
    private String images = readDataFromJson("res_Images");
    private String news = readDataFromJson("res_News");
    private String maps = readDataFromJson("res_Maps");
    private String more = readDataFromJson("res_More");
    private String settings = readDataFromJson("res_Settings");
    private String tools = readDataFromJson("res_Tools");

    public MainPage clickLogo() {
        $(By.xpath(logoLabel)).click();
        $(By.xpath(logoLabel)).shouldBe(Condition.disappear);
        logger.info("Click on Google lol");
        return new MainPage();
    }

    public void checkResultPageIsLoaded() {
        logger.debug(String.format("Startin to check: %s, %s, %s, %s, %s, %s, %s, %s",
                all, videos, images, news, maps, settings, more, tools));
        $(By.xpath(String.format(menuItemsLocatorsLinks, all))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, videos))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, images))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, news))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, maps))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, settings))).shouldBe(Condition.visible);
        $(By.xpath(String.format(menuItemsLocatorsLinks, more))).shouldBe(Condition.visible);
        $(By.xpath(String.format(toolLocatorLabel, tools))).shouldBe(Condition.visible);
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
