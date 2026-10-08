package com.autophile.yatra.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.entity.DriverPasswordResetOtp;
import com.autophile.yatra.repository.DriverPasswordResetOtpRepository;
import com.autophile.yatra.repository.DriverRepository;

@Service
public class DriverPasswordResetService 
{

    private final DriverRepository driverRepository;
    private final DriverPasswordResetOtpRepository otpRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    public DriverPasswordResetService(DriverRepository driverRepository,DriverPasswordResetOtpRepository otpRepository) 
    {

        this.driverRepository = driverRepository;
        this.otpRepository = otpRepository;
    }


    // -----------------------------------------
    // Generate OTP
    // -----------------------------------------

    public String generateOtp(String mobile) 
    {

        if (mobile == null || mobile.isBlank()) 
        {
            throw new RuntimeException("Mobile number is required");
        }

        if (!mobile.matches("\\d{10}")) 
        {
            throw new RuntimeException("Please enter a valid 10 digit mobile number");
        }


        Driver driver = driverRepository.findByMobile(mobile)
                .orElseThrow(() ->
                        new RuntimeException("Driver account not found"));


        if (!driver.isActive()) 
        {
            throw new RuntimeException("Driver account is inactive");
        }


        int otpNumber = 100000 + secureRandom.nextInt(900000);

        String otp = String.valueOf(otpNumber);


        DriverPasswordResetOtp resetOtp = new DriverPasswordResetOtp();

        resetOtp.setMobile(mobile);
        resetOtp.setOtp(otp);

        // OTP valid for 5 minutes
        resetOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        resetOtp.setVerified(false);


        otpRepository.save(resetOtp);


        // Development mode
        // Later this will be replaced by SMS provider.
        System.out.println("======================================");

        System.out.println("Driver Password Reset OTP");

        System.out.println("Mobile : " + mobile);

        System.out.println( "OTP    : " + otp);

        System.out.println("Valid  : 5 minutes");

        System.out.println( "======================================");


        return "OTP generated successfully";
    }


    // -----------------------------------------
    // Verify OTP
    // -----------------------------------------

    public String verifyOtp(String mobile,String otp) 
    {

        if (mobile == null || mobile.isBlank()) 
        {
            throw new RuntimeException( "Mobile number is required");
        }

        if (otp == null || otp.isBlank()) 
        {
            throw new RuntimeException("OTP is required");
        }


        DriverPasswordResetOtp resetOtp = otpRepository
                        .findTopByMobileOrderByIdDesc(mobile)
                        .orElseThrow(() ->
                                new RuntimeException("OTP not found"));


        if (resetOtp.isVerified()) 
        {
            throw new RuntimeException("OTP has already been used");
        }


        if (LocalDateTime.now().isAfter(resetOtp.getExpiryTime())) 
        {

            throw new RuntimeException("OTP has expired");
        }


        if (!resetOtp.getOtp().equals(otp)) 
        {

            throw new RuntimeException("Invalid OTP");
        }


        resetOtp.setVerified(true);

        otpRepository.save(resetOtp);


        return "OTP verified successfully";
    }


    // -----------------------------------------
    // Change Password
    // -----------------------------------------

    public String changePassword(String mobile,String newPassword) 
    {

        if (mobile == null || mobile.isBlank()) 
        {
            throw new RuntimeException("Mobile number is required");
        }

        if (newPassword == null || newPassword.isBlank()) 
        {

            throw new RuntimeException("New password is required");
        }


        if (newPassword.length() < 6) 
        {

            throw new RuntimeException("Password must contain at least 6 characters");
        }


        DriverPasswordResetOtp resetOtp =
                otpRepository
                        .findTopByMobileOrderByIdDesc(mobile)
                        .orElseThrow(() ->
                                new RuntimeException("Please verify OTP first"));


        if (!resetOtp.isVerified())
        {
        	

            throw new RuntimeException("Please verify OTP first");
        }


        if (LocalDateTime.now().isAfter(resetOtp.getExpiryTime())) 
        {

            throw new RuntimeException("OTP verification has expired");
        }


        Driver driver = driverRepository.findByMobile(mobile).orElseThrow(() -> new RuntimeException("Driver account not found"));


        driver.setPassword(newPassword);

        driverRepository.save(driver);


        // Prevent the same OTP from being reused
        resetOtp.setVerified(false);

        otpRepository.save(resetOtp);


        return "Password changed successfully";
    }
}