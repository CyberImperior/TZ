package mingazov.bank.exceptions;

public class CustomerNotExistsException extends RuntimeException{
    public CustomerNotExistsException(String message) {
        super(message);
    }
}
