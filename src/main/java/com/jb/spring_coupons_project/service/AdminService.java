package com.jb.spring_coupons_project.service;

import com.jb.spring_coupons_project.beans.Company;
import com.jb.spring_coupons_project.beans.Customer;
import com.jb.spring_coupons_project.exception.CompanyException;
import com.jb.spring_coupons_project.exception.CustomerException;
import com.jb.spring_coupons_project.exception.ExistsException;
import com.jb.spring_coupons_project.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService extends ClientService {


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }


    //CREATE
    public void addCompany(Company company) throws CompanyException, TokenException {
        if (companyRepository.existsByName(company.getName())) {
            throw new CompanyException("The company is already exists");
        }
        companyRepository.save(company);
    }


    public void addCustomer(Customer customer) throws CustomerException, TokenException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException("There is already a user with this email");
        }
        customerRepository.save(customer);
    }


    //READ
    public Optional<Company> getOneCompany(int id) throws ExistsException, TokenException {
        if (companyRepository.existsById(id)) {
            return companyRepository.findById(id);
        } else {
            throw new ExistsException("Company not exists");
        }
    }


    public List<Company> getAllCompanies() throws ExistsException, TokenException {
        if (companyRepository.findAll().isEmpty()) {
            throw new ExistsException("There is no company exist");
        }
        return companyRepository.findAll();
    }


    public Optional<Customer> getOneCustomer(int id) throws ExistsException, TokenException {
        if (customerRepository.existsById(id)) {
            return customerRepository.findById(id);
        } else {
            throw new ExistsException("Customer not exists");
        }
    }


    public List<Customer> getAllCustomers() throws ExistsException, TokenException {
        if (customerRepository.findAll().isEmpty()) {
            throw new ExistsException("There is no customer exists");
        }
        return customerRepository.findAll();
    }


    //UPDATE
    public void updateCompany(Company company) throws ExistsException, TokenException, CompanyException {
        if (companyRepository.existsById(company.getId())) {
            companyRepository.save(company);
            System.out.println("Company was updated");
        } else {
            throw new ExistsException("Company not exists");
        }
    }


    public void updateCustomer(Customer customer) throws ExistsException, TokenException, CompanyException {
        if (customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
            System.out.println("Company was updated");
        } else {
            throw new ExistsException("Customer not exists");
        }
    }


    //DELETE
    public void deleteCompany(int id) throws ExistsException, TokenException {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            System.out.println("Company was deleted");
        } else {
            throw new ExistsException("Company not exists");
        }
    }


    public void deleteCustomer(int id) throws ExistsException, TokenException {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            System.out.println("Customer was deleted");
        } else {
            throw new ExistsException("Customer not exists");
        }
    }
}
