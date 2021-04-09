package core.drivers.factory;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import core.drivers.SingletonDriverEnum;
import core.drivers.providers.ChromeDriverProvider;
import core.drivers.providers.FireFoxDriverProvider;

/**
 Setup browser. Browser name in config should be equals
    with name in class: {@link com.codeborne.selenide.Browsers}
    Example:
        CHROME = "chrome";
        IE = "ie";
        INTERNET_EXPLORER = "internet explorer";
        EDGE = "edge";
        FIREFOX = "firefox";
 */
public final class DriverFactory {

    public static SelenideDriver initDriver(String browser) {
        switch (browser) {
            case Browsers.CHROME:
                Configuration.browser = ChromeDriverProvider.class.getName();
                break;
            case Browsers.FIREFOX:
                Configuration.browser = FireFoxDriverProvider.class.getName();
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Cannot determine browser type. Passed browser type was: %s", browser));
        }
        return SingletonDriverEnum.getDRIVER();
    }
}
