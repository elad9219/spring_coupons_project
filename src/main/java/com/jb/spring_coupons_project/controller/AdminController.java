package com.jb.spring_coupons_project.controller;


import com.jb.spring_coupons_project.beans.Company;
import com.jb.spring_coupons_project.beans.Customer;
import com.jb.spring_coupons_project.beans.UserType;
import com.jb.spring_coupons_project.exception.*;
import com.jb.spring_coupons_project.security.JWTutil;
import com.jb.spring_coupons_project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/admin")   //http://localhost:8080/admin
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final JWTutil jwtUtil;


    //CREATE
    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws LoginException, TokenException, CompanyException {
        if (jwtUtil.validateToken(token)) {
            adminService.addCompany(company);
            return ResponseEntity.ok()
                    .header("Authorization", jwtUtil.generateToken(token))
                    .body(company.getName() + " added.");
        }
        throw new LoginException();
    }



    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws LoginException, TokenException, CustomerException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        adminService.addCustomer(customer);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customer.getFirst_name() + " " + customer.getLast_name() + " added.");
    }


    //READ
    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws LoginException, TokenException, CompanyException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(adminService.getAllCompanies());
    }




    @GetMapping("/allCustomers") //  http://localhost:8080/customer/allcustomers
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws LoginException, TokenException, CustomerException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(adminService.getAllCustomers());
    }



    @GetMapping("/singleCompany/{id}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws LoginException, TokenException, CompanyException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(adminService.getOneCompany(id));
    }


    @GetMapping("/singleCustomer/{id}")    //http://localhost:8080/customer/2
    public ResponseEntity<?> getCustomerById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws LoginException, TokenException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(adminService.getOneCustomer(id));
    }


    //UPDATE
    @PutMapping("/updateCompany")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws LoginException, TokenException, CompanyException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        adminService.updateCompany(company);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(company.getName() + " updated.");
    }


    @PutMapping("/updateCustomer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws LoginException, TokenException, CustomerException, ExistsException, CompanyException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        adminService.updateCustomer(customer);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(customer.getFirst_name() + " " + customer.getLast_name() + " updated.");
    }



    //DELETE
    @DeleteMapping("/deleteCompany/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws LoginException, TokenException, CompanyException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        adminService.deleteCompany(id);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body("Company deleted.");
    }



    @DeleteMapping("/deleteCustomer/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws LoginException, TokenException, CustomerException, ExistsException {
        jwtUtil.checkUser(token, UserType.ADMIN);
        adminService.deleteCustomer(id);
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body("Customer deleted.");
    }
}
