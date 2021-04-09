package core.drivers.providers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverProvider implements WebDriverProvider, ILocaleReviewer {

    /*
        https://peter.sh/experiments/chromium-command-line-switches/ - chrome options
     */
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        ChromeDriverManager.chromedriver().setup();
        String locale = getLocale();
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chrome_prefs = new HashMap<>();
        chrome_prefs.put("intl.accept_languages", locale);
        chromeOptions.setExperimentalOption("prefs", chrome_prefs);
        chromeOptions.merge(desiredCapabilities);
        return new ChromeDriver(chromeOptions);
    }
}
