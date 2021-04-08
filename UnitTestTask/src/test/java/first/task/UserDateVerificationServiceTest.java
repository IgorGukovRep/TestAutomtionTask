package first.task;

import first.task.controller.UserDateVerificationService;
import first.task.dataProvider.UserDateVerificationServiceProvider;
import first.task.exception.InvalidDateOfBirthException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDateVerificationServiceTest extends BaseTest {

    @Test(dataProviderClass = UserDateVerificationServiceProvider.class,
            dataProvider = "positive data with correct, boundary and leap years values.")
    public void checkUserDateVerificationPositiveDataTest(String date, boolean expectedResult) {
        Assert.assertEquals(UserDateVerificationService.isUserFullAge(date), expectedResult,
                "Date of Birth is incorrectly calculated.");
    }

    @Test(expectedExceptions = InvalidDateOfBirthException.class,
            dataProviderClass = UserDateVerificationServiceProvider.class,
            dataProvider = "negative data with incorrect values and wrong format.")
    public void checkUserDateVerificationNegativeDataTest(String date) {
        UserDateVerificationService.isUserFullAge(date);
    }
}
