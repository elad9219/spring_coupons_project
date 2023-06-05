package com.jb.spring_coupons_project.controller;

import com.jb.spring_coupons_project.beans.Category;
import com.jb.spring_coupons_project.exception.ExistsException;
import com.jb.spring_coupons_project.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")   //http://localhost:8080/guest
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/allSystemCoupons")
    public ResponseEntity<?> getAllCoupons() throws ExistsException {
        return ResponseEntity.ok()
                .body(guestService.getAllSystemCoupons());
    }


    @GetMapping("/allCouponsByCategory/{category}")
    public ResponseEntity<?> getAllCompanyCouponsByCategory(@PathVariable Category category) throws  ExistsException {
        return ResponseEntity.ok()
                .body(guestService.getAllCouponsByCategory(category));
    }


    @GetMapping("/allCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getAllCompanyCouponsByMaxPrice(@PathVariable double maxPrice) throws  ExistsException {
        return ResponseEntity.ok()
                .body(guestService.getAllCouponsByMaxPrice(maxPrice));
    }
}
