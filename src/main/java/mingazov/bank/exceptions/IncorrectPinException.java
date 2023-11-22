package mingazov.bank.exceptions;

public class IncorrectPinException extends RuntimeException{
    public IncorrectPinException(String message) {
        super(message);
    }
}
