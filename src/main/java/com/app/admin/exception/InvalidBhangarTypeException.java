package com.app.admin.exception;

public class InvalidBhangarTypeException extends Exception {

    @Override
    public String getMessage() {
        return "Enter Valid Bhangar Type.";
    }
}
