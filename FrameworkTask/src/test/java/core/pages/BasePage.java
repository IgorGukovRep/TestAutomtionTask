package core.pages;

import com.codeborne.selenide.SelenideDriver;
import core.drivers.SingletonDriverEnum;
import lombok.Getter;

public abstract class BasePage {

    @Getter
    private SelenideDriver driver = SingletonDriverEnum.getDRIVER();


}
