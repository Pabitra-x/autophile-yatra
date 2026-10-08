package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.dto.CustomerRegistrationRequest;
import com.autophile.yatra.entity.Customer;
import com.autophile.yatra.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController 
{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) 
    {

        this.customerService = customerService;
    }

    // =========================================================
    // CUSTOMER REGISTRATION
    // =========================================================

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerRegistrationRequest request) 
    {

        String result =customerService.registerCustomer(request);

        return ResponseEntity.ok(result);
    }

    // =========================================================
    // CUSTOMER LOGIN
    // =========================================================

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String mobile,@RequestParam String password) 
    {

        String result = customerService.loginCustomer(mobile,password);

        return ResponseEntity.ok(result);
    }

    // =========================================================
    // GET ALL CUSTOMERS
    // =========================================================

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() 
    {

        List<Customer> customers = customerService.getAllCustomers();

        return ResponseEntity.ok(customers);
    }
}