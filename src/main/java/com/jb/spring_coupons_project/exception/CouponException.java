package com.jb.spring_coupons_project.exception;

public class CouponException extends Exception {
    public CouponException() {
        super("General coupon exception");
    }


    public CouponException(String message) {
        super(message);
    }
}
