package com.avi.springboot.jpademo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerControllerExecptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
        ErrorMessage message=new ErrorMessage("custome Method Not Supported ",HttpStatus.METHOD_NOT_ALLOWED.value());
        return  new ResponseEntity<>(message,status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleResorseNotFoundExecption(Exception ex,HttpStatus status){
        ErrorMessage message=new ErrorMessage("Custome--"+ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(message,status);
    }
}
