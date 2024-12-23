package exceptions;

// Opdracht 2.2
public class KlantException extends RuntimeException {

    public KlantException() {
        super();
    }

    public KlantException(String message) {
        super(message);
    }

    public KlantException(String message, Throwable cause) {
        super(message, cause);
    }

    public KlantException(Throwable cause) {
        super(cause);
    }

    public KlantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
