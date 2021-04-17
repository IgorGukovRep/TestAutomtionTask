package webtests;

import core.pages.MainPage;
import core.pages.ResultPage;
import core.tests.BaseWebTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckSearchAndNavigationTest extends BaseWebTest {

    private MainPage mainPage;
    private ResultPage resultPage;

    @Override
    protected void customSetUpEach() {
        mainPage = new MainPage();
        resultPage = new ResultPage();
    }


    @Test(description = "Enter Full name and check Results page.")
    @Parameters({"inputForSearch", "result", "lineNumberResult"})
    public void checkSearchAndNavigationTest(String inputForSearch, String result, int lineNumberResult) {
        mainPage.openMainPage();
        mainPage.fillSearchInput(inputForSearch);
        mainPage.clickSearchButton();
        resultPage.checkResultPageIsLoaded();
        Assert.assertEquals(resultPage.getResultLinkName(lineNumberResult), result,
                "First link contains other result:" + resultPage.getResultLinkName(lineNumberResult));
        resultPage.clickLogo();
        Assert.assertTrue(resultPage.isResultLinksDisappeared(), "Result links still exists.");
    }

    //  @Test(description = "Enter Full name and check Results page. Check Parallel")
    //  @Parameters({"inputForSearch", "result", "lineNumberResult"})
    //  public void checkSearchAndNavigationTest_2(String inputForSearch, String result, int lineNumberResult) {
    //      mainPage.openMainPage();
    //      mainPage.fillSearchInput(inputForSearch);
    //      mainPage.clickSearchButton();
    //      resultPage.checkResultPageIsLoaded();
    //      Assert.assertEquals(resultPage.getResultLinkName(lineNumberResult), result,
    //              "First link contains other result:" + resultPage.getResultLinkName(lineNumberResult));
    //  }
}
