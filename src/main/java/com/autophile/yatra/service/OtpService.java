package com.autophile.yatra.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.OtpVerification;
import com.autophile.yatra.repository.OtpVerificationRepository;

@Service
public class OtpService 
{

    private final OtpVerificationRepository otpRepository;

    private final SecureRandom secureRandom =
            new SecureRandom();

    public OtpService(OtpVerificationRepository otpRepository) 
    {

        this.otpRepository = otpRepository;
    }

    // =========================================================
    // GENERATE OTP
    // =========================================================

    public String generateOtp(String mobile) 
    {

        int otpNumber =100000 + secureRandom.nextInt(900000);

        String otp =String.valueOf(otpNumber);

        OtpVerification otpVerification = new OtpVerification();

        otpVerification.setMobile(mobile);

        otpVerification.setOtp(otp);

        // OTP valid for 10 minutes
        otpVerification.setExpiryTime( LocalDateTime.now().plusMinutes(10));

        otpVerification.setVerified(false);

        otpRepository.save(otpVerification);

        // Development mode:
        // Later this OTP will be sent through SMS provider.
        return otp;
    }

    // =========================================================
    // VERIFY OTP
    // =========================================================

    public String verifyOtp(String mobile,String otp) 
    {

        Optional<OtpVerification> optionalOtp =otpRepository.findTopByMobileOrderByOtpIdDesc( mobile);

        if (optionalOtp.isEmpty()) 
        {

            return "OTP not found";
        }

        OtpVerification otpVerification = optionalOtp.get();

        // -----------------------------------------------------
        // ALREADY VERIFIED
        // -----------------------------------------------------

        if (otpVerification.isVerified()) 
        {

            return "OTP already used";
        }

        // -----------------------------------------------------
        // CHECK EXPIRY
        // -----------------------------------------------------

        if (LocalDateTime.now()
                .isAfter(
                    otpVerification.getExpiryTime())) {

            return "OTP expired";
        }

        // -----------------------------------------------------
        // CHECK OTP
        // -----------------------------------------------------

        if (!otpVerification.getOtp()
                .equals(otp)) {

            return "Invalid OTP";
        }

        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        otpVerification.setVerified(true);

        otpRepository.save(otpVerification);

        return "OTP verified successfully";
    }
}