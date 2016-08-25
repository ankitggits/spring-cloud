package no.ankit.exception;

/**
 * Created by AB75448 on 25.08.2016.
 */
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super();
    }
    public ServiceUnavailableException(String s) {
        super(s);
    }
    public ServiceUnavailableException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ServiceUnavailableException(Throwable throwable) {
        super(throwable);
    }
}
