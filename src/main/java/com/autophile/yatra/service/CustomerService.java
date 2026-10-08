package com.autophile.yatra.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.dto.CustomerRegistrationRequest;
import com.autophile.yatra.entity.Customer;
import com.autophile.yatra.repository.CustomerRepository;

@Service
public class CustomerService 
{

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) 
    {

        this.customerRepository = customerRepository;
    }

    // =========================================================
    // CUSTOMER REGISTRATION
    // =========================================================

    public String registerCustomer(CustomerRegistrationRequest request) 
    {

        // -----------------------------------------------------
        // DATE OF BIRTH CHECK
        // -----------------------------------------------------

        if (request.getDateOfBirth() == null) 
        {

            return "Registration failed: Date of birth is required";
        }

        // -----------------------------------------------------
        // 18+ AGE CHECK
        // -----------------------------------------------------

        int age = Period.between(request.getDateOfBirth(),LocalDate.now()).getYears();

        if (age < 18) 
        {

            return "Registration failed: Customer must be 18 or older";
        }

        // -----------------------------------------------------
        // EMAIL CHECK
        // -----------------------------------------------------

        if (customerRepository.existsByEmail(request.getEmail())) 
        {

            return "Registration failed: Email already registered";
        }

        // -----------------------------------------------------
        // MOBILE CHECK
        // -----------------------------------------------------

        if (customerRepository.existsByMobile(request.getMobile())) 
        {

            return "Registration failed: Mobile number already registered";
        }

        // -----------------------------------------------------
        // CREATE CUSTOMER
        // -----------------------------------------------------

        Customer customer = new Customer();

        customer.setName(request.getName());

        customer.setDateOfBirth(request.getDateOfBirth());

        customer.setEmail(request.getEmail());

        customer.setMobile(request.getMobile());

        // Temporary development password.
        // BCrypt will be added later.
        customer.setPassword(request.getPassword());

        customerRepository.save(customer);

        return "Registration successful";
    }

    // =========================================================
    // CUSTOMER LOGIN
    // =========================================================

    public String loginCustomer(String mobile,String password) 
    {

        Optional<Customer> optionalCustomer = customerRepository.findByMobile(mobile);

        if (optionalCustomer.isEmpty()) 
        {

            return "Invalid mobile number or password";
        }

        Customer customer = optionalCustomer.get();

        if (!customer.getPassword().equals(password)) 
        {

            return "Invalid mobile number or password";
        }

        return "Login successful|"+ customer.getCustomerId();
    }

    // =========================================================
    // GET ALL CUSTOMERS
    // =========================================================

    public List<Customer> getAllCustomers() 
    {

        return customerRepository.findAll();
    }
}