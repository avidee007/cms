package com.avi.springboot.jpademo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomerAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> customerNotFoundExceptionHandler(CustomerNotFoundException cnfe) {
        CustomerErrorMessage message = new CustomerErrorMessage(cnfe.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerUpdateFailedExecption.class)
    public ResponseEntity<Object> UpdateFailedExceptionHandler(CustomerUpdateFailedExecption cnfe) {
        CustomerErrorMessage message = new CustomerErrorMessage(cnfe.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> allExceptionHandler(Exception e) {
        CustomerErrorMessage message = new CustomerErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    // Overriding Methods
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomerErrorMessage message = new CustomerErrorMessage("API Endpoint is not available", status.value(), LocalDateTime.now());
        return new ResponseEntity<>(message, status);
    }
}
