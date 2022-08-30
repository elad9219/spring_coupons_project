package com.jb.spring_coupons_project.exception;

public class CompanyException extends Exception {
    public CompanyException() {
        super("General company exception");
    }

    public CompanyException(String message) {
        super(message);
    }
}
