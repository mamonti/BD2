package bd2.nonotion.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ResponseEntity<Object> unavailableBalanceHandler(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> unexpectedHandler(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
