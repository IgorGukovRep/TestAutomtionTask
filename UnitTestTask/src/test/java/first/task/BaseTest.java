package first.task;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseTest {

    @BeforeSuite
    public void setupBeforeSuiteRun(ITestContext context) {
        log.info(String.format("***** Suite '%s' run is started*****".concat(System.lineSeparator()),
                context.getCurrentXmlTest().getSuite().getName()));
    }

    @BeforeMethod
    public void setupBeforeTestRun(ITestResult result) {
        log.info(String.format("***** Test '%s' run is started*****", result.getMethod().getMethodName()));
        log.info("*************************************************");
    }

    @AfterMethod
    public void finishAfterTestRun(ITestResult result) {
        log.info("*************************************************".concat(System.lineSeparator()));
    }

    @AfterSuite()
    public void finishAfterSuiteRun(ITestContext context) {
        log.info(String.format("***** Suite '%s' run is finished *****\n", context.getCurrentXmlTest().getSuite().getName()));
    }

}
