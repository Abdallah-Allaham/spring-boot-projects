package com.abdallah.order_api.repo;

import com.abdallah.order_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByEmail(String email);
}
