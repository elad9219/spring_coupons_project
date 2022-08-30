package com.jb.spring_coupons_project.advice;


import com.jb.spring_coupons_project.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import java.security.SignatureException;

@RestController
@ControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(value = {LoginException.class, com.jb.spring_coupons_project.exception.LoginException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail invalidLogin(Exception err) {
        return new ErrorDetail("Bad login", err.getMessage());
    }


    @ExceptionHandler(value = {TokenException.class, SignatureException.class, MalformedJwtException.class, ExpiredJwtException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetail invalidToken(Exception err) {
        return new ErrorDetail("Invalid token", err.getMessage());
    }
}
