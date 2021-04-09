package core.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static controller.config.DictionaryFileManager.readDataFromJson;
import static core.pages.MainPage.ElementTitles.SEARCH_BUTTON;
import static core.pages.MainPage.ElementTitles.SEARCH_TITLE;

public class MainPage extends BasePage {

    private final String searchLocatorInput = "//input[@title='%s']";
    private final String searchLocatorButton = "//input[@value='%s']";

    public enum ElementTitles {

        SEARCH_TITLE(readDataFromJson("res_Search")),
        SEARCH_BUTTON(readDataFromJson("res_Search_In_Google"));

        @Getter
        private String value;

        ElementTitles(String value) {
            this.value = value;
        }
    }

    public MainPage openMainPage() {
        getDriver().open("/");
        return this;
    }

    public MainPage fillSearchInput(String text) {
        SelenideElement searchInput = $(By.xpath(String.format(searchLocatorInput, SEARCH_TITLE.getValue())));
        searchInput.val(text);
        return this;
    }

    public ResultPage clickSearchButton() {
        SelenideElement searchButton = $(By.xpath(String.format(searchLocatorButton, SEARCH_BUTTON.getValue())));
        searchButton.click();
        return new ResultPage();
    }


    public String getHintName() {
        return null;
    }

    public void moveMouseOnSearchField() {

    }

    public String getPageTitleFromNavigationBar() {
        return null;
    }
}
