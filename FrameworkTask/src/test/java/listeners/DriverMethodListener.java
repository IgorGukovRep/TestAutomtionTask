package listeners;

import controller.config.WebConfigManager;
import core.drivers.factory.DriverFactory;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utils.BrowserConsoleUtility;

import java.util.List;
import java.util.Objects;

public class DriverMethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            if (Objects.isNull(browserName)) {
                browserName = WebConfigManager.DEFAULT_BROWSER;
            }
            DriverFactory.initDriver(browserName);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && method.getTestMethod().getXmlTest().getLocalParameters().get("browserName").equals("chrome")) {
            List<String> errorList = BrowserConsoleUtility.analyzeBrowserConsoleLogError();
            List<String> warningList = BrowserConsoleUtility.analyzeBrowserConsoleLogWarning();
            Assert.assertTrue(errorList.isEmpty(), String.format("Unexpected Error in browser console - is present. Error message: %s", errorList));
            Assert.assertTrue(warningList.isEmpty(), String.format("Expected Warning in browser console is present. Warning message: %s", warningList));
        }
    }
}
