package mingazov.bank.exceptions;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
