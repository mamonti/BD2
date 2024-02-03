package bd2.nonotion.advice;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ResponseEntity<Object> notFoundHandler(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class
    })
    protected ResponseEntity<Object> badRequestHandler(MethodArgumentNotValidException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.MULTI_STATUS,
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> unexpectedHandler(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
