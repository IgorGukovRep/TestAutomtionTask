package first.task.dataProvider;

import first.task.utils.DateUtils;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;

public class UserDateVerificationServiceProvider {

    @DataProvider(name = "positive data with correct, boundary and leap years values.")
    public static Object[][] positiveDataForTest() {
        String boundaryFullAge = DateUtils.convertLocalDateInStringFormat(LocalDate.now().minusYears(18).minusDays(1));
        String boundaryNotFullAge = DateUtils.convertLocalDateInStringFormat(LocalDate.now().minusYears(18));
        String boundaryNow = DateUtils.convertLocalDateInStringFormat(LocalDate.now());
        return new Object[][]{
                new Object[]{"12/31/1988", true}, /* Correct data */
                new Object[]{"02/29/2000", true}, /* Leap years */
                new Object[]{"02/29/2020", false},
                new Object[]{"01/01/1903", true}, /* Boundary values */
                new Object[]{boundaryFullAge, true},
                new Object[]{boundaryNotFullAge, false},
                new Object[]{boundaryNow, false},
        };
    }

    @DataProvider(name = "negative data with incorrect values and wrong format.")
    public static Object[][] negativeDataForTest() {
        String futureDate = DateUtils.convertLocalDateInStringFormat(LocalDate.now().plusDays(1));
        return new Object[][]{
                new Object[]{"12/31/1902"},
                new Object[]{"13/03/1950"},
                new Object[]{"12/32/1950"},
                new Object[]{"12/31/0000"},
                new Object[]{"12/31/0000"},
                new Object[]{"1/12/1930"},
                new Object[]{"01/9/1980"},
                new Object[]{"01/03/108"},
                new Object[]{"05/25"},
                new Object[]{"01\\05\\1955"},
                new Object[]{""},
                new Object[]{null},
                new Object[]{"letters"},
                new Object[]{"@?*.#"},
                new Object[]{"01.05.1990"},
                new Object[]{"01-05-1990"},
                new Object[]{"01:05:1980"},
                new Object[]{"Jan-01-2001"},
                new Object[]{"-5/-2/-1950"},
                new Object[]{futureDate},
        };
    }
}
