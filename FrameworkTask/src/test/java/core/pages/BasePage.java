package core.pages;

import com.codeborne.selenide.SelenideDriver;
import core.drivers.SingletonDriverEnum;
import logger.Logger;
import lombok.Getter;

public abstract class BasePage {

    protected static Logger logger = Logger.getInstance(BasePage.class);

    @Getter
    private SelenideDriver driver = SingletonDriverEnum.getDRIVER();


}
