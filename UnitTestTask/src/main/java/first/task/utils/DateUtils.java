package first.task.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String DATE_PATTERN = "MM/dd/yyyy";
    public static final DateTimeFormatter PATTERN_DATE_FORMAT_DEFAULT = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static int getPeriodInYears(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getYears();
    }

    public static LocalDate convertIntoLocalDate(String date, DateTimeFormatter formatter) {
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate convertIntoLocalDate(String date) {
        return LocalDate.parse(date, PATTERN_DATE_FORMAT_DEFAULT);
    }

    public static String convertLocalDateInStringFormat(LocalDate date) {
        return convertLocalDateInStringFormat(date, PATTERN_DATE_FORMAT_DEFAULT);
    }

    public static String convertLocalDateInStringFormat(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }
}
