package com.jb.spring_coupons_project.repository;

import com.jb.spring_coupons_project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    boolean existsByEmail(String email);

    Optional <Customer> findByEmailAndPassword(String email, String password);

    boolean existsById(int id);
}
