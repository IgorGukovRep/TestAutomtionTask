package first.task;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

@Slf4j
public class BaseTest {

    @BeforeSuite
    public void setupBeforeSuiteRun(ITestContext context) {
        log.info(String.format("***** Suite '%s' run is started*****", context.getCurrentXmlTest().getSuite().getName()));
    }

    @BeforeTest(alwaysRun = true)
    public void setupBeforeTestRun(ITestContext context) {
        log.info(String.format("***** Test '%s' run is started*****", context.getName()));
    }

    @AfterTest(alwaysRun = true)
    public void finishAfterTestRun(ITestContext context) {
        log.info(String.format("***** Test '%s' run is finished*****", context.getName()));
    }

    @AfterSuite()
    public void finishAfterSuiteRun(ITestContext context) {
        log.info(String.format("***** Suite '%s' run is finished *****", context.getCurrentXmlTest().getSuite().getName()));
    }

}
