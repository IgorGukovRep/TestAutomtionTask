package core.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static controller.config.DictionaryFileManager.readDataFromJson;

public class MainPage extends BasePage {

    private final String searchLocatorInput = "//input[@title='%s']";
    private final String searchLocatorButton = "//input[@value='%s']";

    private String search_title = readDataFromJson("res_Search");
    private String search_button = readDataFromJson("res_Search_In_Google");


    public MainPage openMainPage() {
        getDriver().open("/");
        logger.info("Open main page: https://google.com");
        return this;
    }

    public MainPage fillSearchInput(String text) {
        SelenideElement searchInput = $(By.xpath(String.format(searchLocatorInput, search_title)));
        searchInput.val(text);
        logger.info(String.format("Input value: %s", text));
        return this;
    }

    public ResultPage clickSearchButton() {
        SelenideElement searchButton = $(By.xpath(String.format(searchLocatorButton, search_button)));
        searchButton.click();
        logger.info("Click Search button");
        return new ResultPage();
    }
}
