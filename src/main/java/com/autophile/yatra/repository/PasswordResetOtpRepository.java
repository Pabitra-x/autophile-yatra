package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.PasswordResetOtp;

public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtp, Long> 
{

    Optional<PasswordResetOtp>
    findTopByMobileAndUserTypeOrderByIdDesc(String mobile,String userType);
}