package com.app.admin.exception;

public class InvalidBhangarItemIdException extends Exception {

    @Override
    public String getMessage() {
        return "Enter Valid Bhangar Item Id.";
    }

}
