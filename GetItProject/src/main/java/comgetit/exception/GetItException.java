package comgetit.exception;

public abstract class GetItException extends RuntimeException {

    public GetItException(final String message) {
        super(message);
    }

    public String getInformationalMessage() {
        return getMessage();
    }
}
