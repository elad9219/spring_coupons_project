package com.jb.spring_coupons_project.exception;

public class ExistsException extends Exception {

    public ExistsException() {
        super("Not exists");
    }

    public ExistsException(String message) {
        super(message);
    }
}