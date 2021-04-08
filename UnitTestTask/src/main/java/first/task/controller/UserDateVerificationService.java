package first.task.controller;

import first.task.exception.DateOfBirthException;
import first.task.exception.InvalidDateOfBirthException;
import first.task.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

import static first.task.exception.InvalidDateOfBirthException.DefaultMessages.*;

@Slf4j
public class UserDateVerificationService {


    public static boolean isUserFullAge(String dateOfBirth) {
        int fullAge = 18;
        log.debug(String.format("Date of birth with value: '%s'", dateOfBirth));
        validateDateOfBirth(dateOfBirth);
        LocalDate startDate = DateUtils.convertIntoLocalDate(dateOfBirth).plusDays(1);
        LocalDate endDate = LocalDate.now();
        int differenceInYears = DateUtils.getPeriodInYears(startDate, endDate);
        log.debug(String.format("Period in years between 'date of birth': '%s'; and 'current date': '%s' is %d years",
                dateOfBirth, DateUtils.convertLocalDateInStringFormat(endDate), differenceInYears));
        return differenceInYears >= fullAge;
    }

    private static void validateDateOfBirth(String dateOfBirth) {
        log.debug("Validate date of birth");
        LocalDate convertedDateOfBirth;
        if (Objects.isNull(dateOfBirth)) {
            throw new InvalidDateOfBirthException(DATE_OF_BIRTH_CANNOT_BE_NULL);
        } else if (dateOfBirth.isEmpty()) {
            throw new InvalidDateOfBirthException(DATE_OF_BIRTH_CANNOT_BE_EMPTY);
        } else if (dateOfBirth.length() != DateUtils.DATE_PATTERN.length()) {
            throw new InvalidDateOfBirthException(DATE_OF_BIRTH_WRONG_FORMAT, dateOfBirth);
        }
        try {
            convertedDateOfBirth = DateUtils.convertIntoLocalDate(dateOfBirth);
        } catch (DateTimeException e) {
            log.debug(e.getMessage());
            throw new InvalidDateOfBirthException(e.getMessage(), e.getCause());
        }
        validateDateOfBirth(convertedDateOfBirth);
    }

    private static void validateDateOfBirth(LocalDate convertedDateOfBirth) throws DateOfBirthException {
        if (convertedDateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateOfBirthException(DATE_OF_BIRTH_LATTER_THAN_NOW, convertedDateOfBirth);
        } else if (convertedDateOfBirth.isBefore(getBoundaryDate())) {
            throw new InvalidDateOfBirthException(DATE_OF_BIRTH_EARLIER_THAN_1903, convertedDateOfBirth);
        }
    }

    private static LocalDate getBoundaryDate() {
        return LocalDate.ofYearDay(1903, 1);
    }
}
