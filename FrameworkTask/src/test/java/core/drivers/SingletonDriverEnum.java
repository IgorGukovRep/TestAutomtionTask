package core.drivers;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.impl.ThreadLocalSelenideDriver;
import lombok.Getter;

public enum SingletonDriverEnum {
    DRIVER_INSTANCE;

    @Getter
    public final static SelenideDriver DRIVER = new ThreadLocalSelenideDriver();

}
