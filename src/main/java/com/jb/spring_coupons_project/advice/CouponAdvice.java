package com.jb.spring_coupons_project.advice;

import com.jb.spring_coupons_project.exception.CouponException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CouponAdvice {
    @ExceptionHandler(value = {CouponException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail couponException(Exception err) {
        return new ErrorDetail("Coupon problem", err.getMessage());
    }
}
