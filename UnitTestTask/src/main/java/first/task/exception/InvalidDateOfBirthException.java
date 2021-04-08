package first.task.exception;

import first.task.utils.DateUtils;
import lombok.Getter;

import java.time.LocalDate;

public class InvalidDateOfBirthException extends DateOfBirthException {

    @Getter
    public enum DefaultMessages {

        DATE_OF_BIRTH_LATTER_THAN_NOW("Wrong argument! The date of birth cannot be later than now!"),
        DATE_OF_BIRTH_EARLIER_THAN_1903("Wrong argument! The date of birth cannot earlier than 1903 year!"),
        DATE_OF_BIRTH_CANNOT_BE_NULL("Wrong argument! The date of birth cannot be null!"),
        DATE_OF_BIRTH_CANNOT_BE_EMPTY("Wrong argument! The date of birth cannot be empty!"),
        DATE_OF_BIRTH_WRONG_FORMAT("Wrong date format!");

        private String message;

        DefaultMessages(String message) {
            this.message = message;
        }
    }

    public InvalidDateOfBirthException(String message) {
        super(message);
    }

    public InvalidDateOfBirthException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateOfBirthException(DefaultMessages message) {
        this(message.getMessage());
    }

    public InvalidDateOfBirthException(DefaultMessages message, String date) {
        this(String.format("%s Invalid date: %s", message.getMessage(), date));
    }

    public InvalidDateOfBirthException(DefaultMessages message, LocalDate date) {
        this(message, DateUtils.convertLocalDateInStringFormat(date));
    }
}
