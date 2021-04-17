package core.drivers.providers;

import org.testng.Reporter;

import java.util.Objects;

import static controller.config.WebConfigManager.BROWSER_LANGUAGE;
import static utils.constants.ConstantsXmlParameters.LOCALE;

public interface ILocaleReviewer {

    default String getLocale() {
        String locale = Reporter.getCurrentTestResult().getTestContext()
                .getCurrentXmlTest().getParameter(LOCALE);
        if (Objects.isNull(locale) || locale.isEmpty()) {
            return BROWSER_LANGUAGE;
        } else {
            return locale;
        }
    }
}
