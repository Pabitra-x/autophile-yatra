package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> 
{

    Optional<Driver> findByMobile(String mobile);

    boolean existsByMobile(String mobile);
}