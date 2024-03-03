package az.ingress.bankapp.exception;

import az.ingress.bankapp.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException exception, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getContextPath() + request.getServletPath())
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(INTERNAL_SERVER_ERROR)
//    public ErrorResponse exceptionHandler(Exception exception, HttpServletRequest request) {
//        return ErrorResponse.builder()
//                .timestamp(LocalDateTime.now())
//                .status(INTERNAL_SERVER_ERROR.value())
//                .message(exception.getMessage())
//                .path(request.getContextPath() + request.getServletPath())
//                .build();
//    }
}
