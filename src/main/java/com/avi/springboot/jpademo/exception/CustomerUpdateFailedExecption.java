package com.avi.springboot.jpademo.exception;

public class CustomerUpdateFailedExecption extends RuntimeException {

    public CustomerUpdateFailedExecption(String message) {
        super(message);
    }
}
