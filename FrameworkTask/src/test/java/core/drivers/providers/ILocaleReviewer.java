package core.drivers.providers;

import org.testng.Reporter;

import java.util.Objects;

import static controller.config.WebConfigManager.BROWSER_LANGUAGE;

public interface ILocaleReviewer {

    default String getLocale() {
        String locale = Reporter.getCurrentTestResult().getTestContext()
                .getCurrentXmlTest().getParameter("locale");
        if (Objects.isNull(locale) || locale.isEmpty()) {
            return BROWSER_LANGUAGE;
        } else {
            return locale;
        }
    }
}
