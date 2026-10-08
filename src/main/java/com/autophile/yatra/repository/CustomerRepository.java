package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> 
{

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Optional<Customer> findByMobile(String mobile);
}