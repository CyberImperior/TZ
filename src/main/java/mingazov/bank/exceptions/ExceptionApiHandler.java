package mingazov.bank.exceptions;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> customerAlreadyExistsException(CustomerAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(IncorrectNumberAccountException.class)
    public ResponseEntity<ErrorMessage> incorrectNumberAccountException(IncorrectNumberAccountException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(IncorrectPinException.class)
    public ResponseEntity<ErrorMessage> incorrectPinException(IncorrectPinException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorMessage> notEnoughMoneyException(NotEnoughMoneyException exception) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(CustomerNotExistsException.class)
    public ResponseEntity<ErrorMessage> customerNotExistsException(CustomerNotExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

}
