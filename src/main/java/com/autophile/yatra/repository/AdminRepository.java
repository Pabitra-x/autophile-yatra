package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Admin;

public interface AdminRepository
        extends JpaRepository<Admin, Long> 
{

    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByMobile(String mobile);

    boolean existsByUsername(String username);

    boolean existsByMobile(String mobile);
}