package com.jb.spring_coupons_project.security;


import com.jb.spring_coupons_project.beans.UserDetails;
import com.jb.spring_coupons_project.beans.UserType;
import com.jb.spring_coupons_project.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTutil {
    //type of encryption - אלגוריתם הצפנה - header
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //our private key
    private String encodedSecretKey = "this+is+my+token+for+coupon+project+spring+nothing";
    //create our private key
    private Key decodedSecurityKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    /**
     * create payload
     * we need user email, password and role for create the token
     * since the userDetail is an instance of the information in the hashcode
     * the token need to get claims which is the information in the hashcode
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", userDetails.getUserType());
        return createToken(claims, userDetails.getEmail());
    }


    public String generateToken(String token) {
        Map<String, Object> claims = new HashMap<>();
        Claims ourClaims = extractAllClaims(token);
        claims.put("userType", ourClaims.get("userType"));
        return createToken(claims, ourClaims.getSubject());
    }


    //we create the JWT token from the information that we got
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now(); //get the current time
        return "Bearer " +
                Jwts.builder()
                .setClaims(claims) //get claims
                .setSubject(email) //get subject
                .setIssuedAt(Date.from(now)) //get current time
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))  //expiration date
                .signWith(this.decodedSecurityKey)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(decodedSecurityKey) //provide our secret key
                .build();
        return jwtParser.parseClaimsJws(token.replace("Bearer", "")).getBody();
    }

    public String extractEmail(String token) throws SignatureException {
        return extractAllClaims(token).getSubject();
    }

    public java.util.Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }


    public String getUserType(String token) {
        Claims claims = extractAllClaims(token);
        return (String) claims.get("userType");
    }

    public boolean validateToken(String token, UserDetails userDetails) throws MalformedJwtException, SignatureException {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public boolean validateToken(String token) throws MalformedJwtException, SignatureException {
        final Claims claims = extractAllClaims(token.replace("Bearer", ""));
        return true;
    }


    public void checkUser(String token, UserType userType) throws LoginException, TokenException {
        if (validateToken(token)) {
            if (!getUserType(token).equalsIgnoreCase(userType.toString())) {
                throw new LoginException("User not allowed");
            }
        } else {
            throw new TokenException();
        }
    }
}