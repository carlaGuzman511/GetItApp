package comgetit.user.exception;

import comgetit.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    private static final String MESSAGE = "El usuario no existe";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
