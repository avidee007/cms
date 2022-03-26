package com.avi.springboot.jpademo.exception;

public class UpdateFailedExecption extends RuntimeException {

    public UpdateFailedExecption(String message) {
        super(message);
    }
}
