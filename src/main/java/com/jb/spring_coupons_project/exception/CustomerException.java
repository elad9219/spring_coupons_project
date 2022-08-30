package com.jb.spring_coupons_project.exception;

public class CustomerException extends Exception {
    public CustomerException() {
        super("General customer exception");
    }

    public CustomerException(String message) {
        super(message);
    }
}
