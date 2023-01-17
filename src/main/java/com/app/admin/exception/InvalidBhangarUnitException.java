package com.app.admin.exception;

public class InvalidBhangarUnitException extends Exception {

    @Override
    public String getMessage() {
        return "Enter Valid Bhangar Unit.";
    }
}
