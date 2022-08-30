package com.jb.spring_coupons_project.beans;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetails {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
