package listeners;

import controller.config.WebConfigManager;
import core.drivers.factory.DriverFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

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
        //TODO: analyze console log
    }
}
