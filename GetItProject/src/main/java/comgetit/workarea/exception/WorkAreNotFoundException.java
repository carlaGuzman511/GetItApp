package comgetit.workarea.exception;

import comgetit.exception.NotFoundException;

public class WorkAreNotFoundException extends NotFoundException {

    private static final String MESSAGE = "El area de trabajo no existe";

    public WorkAreNotFoundException() {
        super(MESSAGE);
    }
}
