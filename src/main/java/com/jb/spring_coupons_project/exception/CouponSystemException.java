package com.jb.spring_coupons_project.exception;

public class CouponSystemException extends Exception {

    private static final long serialVersionUID = 1L;

    public CouponSystemException() {
    }

    public CouponSystemException(String message) {
        super(message);
    }

    public CouponSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponSystemException(Throwable cause) {
        super(cause);
    }

    public CouponSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
