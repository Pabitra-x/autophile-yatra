package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.DriverPasswordResetOtp;

public interface DriverPasswordResetOtpRepository extends JpaRepository<DriverPasswordResetOtp, Long> 
{

    Optional<DriverPasswordResetOtp>
    findTopByMobileOrderByIdDesc(String mobile);
}