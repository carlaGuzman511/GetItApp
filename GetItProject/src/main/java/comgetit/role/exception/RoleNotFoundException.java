package comgetit.role.exception;

import comgetit.exception.NotFoundException;

public class RoleNotFoundException extends NotFoundException {

    private static final String MESSAGE = "El %s role no existe";

    public RoleNotFoundException(String role) {
        super(String.format(MESSAGE, role));
    }
}