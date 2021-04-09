package webtests;

import core.pages.MainPage;
import core.tests.BaseWebTest;
import org.testng.annotations.Test;

public class CheckHintTextOnMainPageTest extends BaseWebTest {

    private final MainPage mainStep = new MainPage();

    @Test
    public void checkSearchHintTest(String language) {
        mainStep.openMainPage();
    }
}
