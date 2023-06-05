package com.jb.spring_coupons_project.service;


import com.jb.spring_coupons_project.beans.UserType;
import com.jb.spring_coupons_project.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;


    public ClientService login(String email, String password, UserType userType) throws LoginException {
        ClientService clientService = null;
        switch (userType) {
            case ADMIN:
                clientService = adminService;
                break;
            case COMPANY:
                clientService = companyService;
                break;
            case CUSTOMER:
                clientService = customerService;
                break;
        }
        if (clientService.login(email, password)) {
            return clientService;
        } else {
            throw new LoginException("There is a problem logging in, check the email, password and user type again.");
        }
    }
}
