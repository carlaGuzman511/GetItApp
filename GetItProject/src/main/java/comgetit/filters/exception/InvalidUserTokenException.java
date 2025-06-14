package comgetit.filters.exception;

import comgetit.exception.GetItException;

public class InvalidUserTokenException extends GetItException {

    private static final String MESSAGE = "Invalid Token";

    public InvalidUserTokenException() {
        super(MESSAGE);
    }
}
