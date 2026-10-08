package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.OtpVerification;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> 
{

    Optional<OtpVerification>
    findTopByMobileOrderByOtpIdDesc(String mobile);
}