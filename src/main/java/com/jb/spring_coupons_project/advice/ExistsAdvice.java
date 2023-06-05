package com.jb.spring_coupons_project.advice;

import com.jb.spring_coupons_project.exception.ExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExistsAdvice {

    @ExceptionHandler(value = {ExistsException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail notExists(Exception err) {
        return new ErrorDetail("Not exists", err.getMessage());
    }
}
