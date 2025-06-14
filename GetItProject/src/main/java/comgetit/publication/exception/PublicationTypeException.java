package comgetit.publication.exception;

import comgetit.exception.GetItException;

public class PublicationTypeException extends GetItException {

    private static final String MESSAGE = "El %s no es un tipo de publicacion";

    public PublicationTypeException(String typePublication) {
        super(String.format(MESSAGE, typePublication));
    }
}
