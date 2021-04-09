package core.tests;

import controller.config.DictionaryFileManager;
import controller.config.WebConfigManager;
import listeners.DriverMethodListener;
import org.testng.annotations.Listeners;

@Listeners(DriverMethodListener.class)
public abstract class BaseWebTest extends BaseTest {

    public static WebConfigManager webConfigManager = new WebConfigManager();
    public static DictionaryFileManager dictionaryFileManager = new DictionaryFileManager();

    @Override
    protected void customSetUpEach() {
    }

    @Override
    protected void customTearDown() {

    }

    @Override
    protected void globalTearDown() {
        DictionaryFileManager.clear();
        logger.info("Clear dictionary file manager!");
    }
}
