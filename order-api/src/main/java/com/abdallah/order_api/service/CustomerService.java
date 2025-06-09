package com.abdallah.order_api.service;

import com.abdallah.order_api.model.Customer;
import com.abdallah.order_api.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepo
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalArgumentException
                    ("Email " + customer.getEmail() + " is already exist");
        }
        return customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Optional<Customer> updateCustomer(Long id,Customer updatedCustomer){
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();

            Optional<Customer> customerCheckEmail= customerRepo
                    .findCustomerByEmail(updatedCustomer.getEmail());
            if (customerCheckEmail.isPresent() &&
                    !customerCheckEmail.get().getId().equals(updatedCustomer.getId())){
                throw new IllegalArgumentException();
            }

            customer.setName(updatedCustomer.getName());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setEmail(updatedCustomer.getEmail());

            return Optional.of(customerRepo.save(customer));
        }
        return Optional.empty();
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
}
