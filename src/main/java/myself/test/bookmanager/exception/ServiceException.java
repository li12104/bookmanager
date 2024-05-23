package myself.test.bookmanager.exception;

public class ServiceException extends RuntimeException{


    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
