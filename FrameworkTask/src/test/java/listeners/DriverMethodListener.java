package listeners;

import com.codeborne.selenide.Browsers;
import controller.config.WebConfigManager;
import core.drivers.SingletonDriverEnum;
import core.drivers.factory.DriverFactory;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utils.BrowserConsoleUtility;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static utils.constants.ConstantsXmlParameters.BROWSER_NAME;

public class DriverMethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getMethod().isBeforeMethodConfiguration()) {
            String browserName;
            if (isXmlTest(method)) {
                browserName = isBrowserPresentInXml(method) ?
                        getXmlLocalParameters(method).get(BROWSER_NAME) : WebConfigManager.DEFAULT_BROWSER;
            } else {
                browserName = WebConfigManager.DEFAULT_BROWSER;
            }
            DriverFactory.initDriver(browserName);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && SingletonDriverEnum.getDRIVER().config().browser().equals(Browsers.CHROME)) {
            List<String> errorList = BrowserConsoleUtility.analyzeBrowserConsoleLogError();
            List<String> warningList = BrowserConsoleUtility.analyzeBrowserConsoleLogWarning();
            Assert.assertTrue(errorList.isEmpty(),
                    String.format("Unexpected Error in browser console - is present. Error message: %s", errorList));
            Assert.assertTrue(warningList.isEmpty(),
                    String.format("Expected Warning in browser console is present. Warning message: %s", warningList));
        }
    }

    private boolean isXmlTest(IInvokedMethod method) {
        return Objects.nonNull(method.getTestMethod().getXmlTest());
    }

    private boolean isBrowserPresentInXml(IInvokedMethod method) {
        return Objects.nonNull(getXmlLocalParameters(method).get(BROWSER_NAME));
    }

    private Map<String, String> getXmlLocalParameters(IInvokedMethod method) {
        return method.getTestMethod().getXmlTest().getLocalParameters();
    }
}
