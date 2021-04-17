package webtests;

import core.pages.MainPage;
import core.pages.ResultPage;
import core.tests.BaseWebTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WithoutParamsTest extends BaseWebTest {

    private MainPage mainPage;
    private ResultPage resultPage;

    @Override
    protected void customSetUpEach() {
        mainPage = new MainPage();
        resultPage = new ResultPage();
    }


    @Test(description = "Enter Full name and check Results page.")
    public void checkSearchAndNavigationTest() {
        mainPage.openMainPage();
        mainPage.fillSearchInput("Александр Сергеевич Пушкин");
        mainPage.clickSearchButton();
        resultPage.checkResultPageIsLoaded();
        Assert.assertEquals(resultPage.getResultLinkName(1), "Пушкин, Александр Сергеевич — Википедия",
                "First link contains other result:" + resultPage.getResultLinkName(1));
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
