package core.tests;

import logger.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    protected static final Logger logger = Logger.getInstance();

    @BeforeSuite
    protected static void setUp() {
    }

    @BeforeMethod
    protected void setUpEach() {
        this.customSetUpEach();
        logger.testStartInfo(getClass());
    }

    protected abstract void customSetUpEach();

    @AfterMethod
    protected void tearDownEach() {
        customTearDown();
    }

    protected abstract void customTearDown();

}
