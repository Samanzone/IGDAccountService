package com.igd.account.exception;


import com.igd.account.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
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
    public @ResponseBody ExceptionResponse handleAccountNotFoundException(final AccountNotFoundException exception,
                                                                          final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();


        return error;
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleNoDataFoundException(final NoDataFoundException exception,
                                                                      final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        ExceptionResponse error = ExceptionResponse.builder()
                .errorMessage(exception.getMessage()).requestedURI(request.getRequestURI()).build();
        return error;
    }
}