package first.task.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DateOfBirthException extends RuntimeException {

    public DateOfBirthException(String message) {
        super(message);
        log.error(message);
    }

    public DateOfBirthException(String message, Throwable cause) {
        super(message, cause);
    }
}
