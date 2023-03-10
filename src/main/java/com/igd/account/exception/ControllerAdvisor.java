package com.igd.account.exception;


import com.igd.account.dto.ExceptionResponse;
import com.igd.account.exception.AccountNotFoundException;
import com.igd.account.exception.NoDataFoundException;
import com.igd.account.exception.UserNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @Order(1)
    public @ResponseBody ExceptionResponse handleAccountNotFoundException(final AccountNotFoundException exception,
                                                                          final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();


        return error;
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @Order(2)
    public @ResponseBody ExceptionResponse handleUserNotExistException(final UserNotExistException exception,
                                                                          final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();


        return error;
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @Order(3)
    public @ResponseBody ExceptionResponse handleNoDataFoundException(final NoDataFoundException exception,
                                                                      final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();

        return error;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @Order(4)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage("Internal Server Error").requestedURI(request.getRequestURI()).build();
        return error;
    }
}