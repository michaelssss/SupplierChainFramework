package com.michaelssss.rzzl2;

public class BusinessException extends Exception {
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
