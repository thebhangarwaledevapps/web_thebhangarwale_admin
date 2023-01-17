package com.app.admin.exception;

public class NoBhangarFoundException extends Exception {

    @Override
    public String getMessage() {
        return "No Bhangar Found.";
    }

}
