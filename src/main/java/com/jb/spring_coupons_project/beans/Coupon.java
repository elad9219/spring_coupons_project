package com.jb.spring_coupons_project.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private int id;
    private int companyId;
    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private Category category;
    @Column(length = 25, nullable = false)
    private String title;
    @Column(length = 40, nullable = false)
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate end_date;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    private String image;
}

