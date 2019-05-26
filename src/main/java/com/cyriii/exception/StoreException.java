package com.cyriii.exception;

public class StoreException extends Exception {

    private String message;

    public StoreException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
