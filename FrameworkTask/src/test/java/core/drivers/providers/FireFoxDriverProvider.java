package core.drivers.providers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

public class FireFoxDriverProvider implements WebDriverProvider, ILocaleReviewer {

    /*
        http://kb.mozillazine.org/About:config_entries - firefox options
     */
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        FirefoxDriverManager.firefoxdriver().setup();
        String locale = getLocale();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("intl.accept_languages", locale);
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.merge(desiredCapabilities);
        return new FirefoxDriver(firefoxOptions);
    }
}