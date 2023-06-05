package com.jb.spring_coupons_project.advice;


import com.jb.spring_coupons_project.exception.CompanyDeletionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CompanyDeletionAdvice {
    @ExceptionHandler(value = {CompanyDeletionException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail deletion(Exception err) {
        return new ErrorDetail("Delete Error", err.getMessage());
    }
}
