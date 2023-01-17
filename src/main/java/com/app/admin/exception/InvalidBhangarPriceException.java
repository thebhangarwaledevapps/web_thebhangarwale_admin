package com.app.admin.exception;

public class InvalidBhangarPriceException extends Exception {

    @Override
    public String getMessage() {
        return "Enter Valid Bhangar Price.";
    }
}
