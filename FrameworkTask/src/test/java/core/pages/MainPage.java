package core.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static controller.config.DictionaryFileManager.readDataFromJson;

public class MainPage extends BasePage {

    private final String searchLocatorInput = "//input[@title='%s']";
    private final String searchLocatorButton = "//input[@value='%s']";

    private String search_title = readDataFromJson("res_Search");
    private String search_button = readDataFromJson("res_Search_In_Google");


    public void openMainPage() {
        getDriver().open("/");
        logger.info("Open main page: https://google.com");
    }

    public void fillSearchInput(String text) {
        $(By.xpath(String.format(searchLocatorInput, search_title))).val(text);
        logger.info(String.format("Input value: %s", text));
    }

    public void clickSearchButton() {
        $(By.xpath(String.format(searchLocatorButton, search_button))).click();
        logger.info("Click Search button");
    }
}
