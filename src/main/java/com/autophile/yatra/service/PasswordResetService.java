package com.autophile.yatra.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Admin;
import com.autophile.yatra.entity.Customer;
import com.autophile.yatra.entity.PasswordResetOtp;
import com.autophile.yatra.repository.AdminRepository;
import com.autophile.yatra.repository.CustomerRepository;
import com.autophile.yatra.repository.PasswordResetOtpRepository;

@Service
public class PasswordResetService 
{

    private final PasswordResetOtpRepository otpRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordResetService(
            PasswordResetOtpRepository otpRepository,
            CustomerRepository customerRepository,
            AdminRepository adminRepository) 
    {

        this.otpRepository = otpRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    // =========================================================
    // GENERATE FORGOT PASSWORD OTP
    // =========================================================

    public String generateOtp(String mobile,String userType) 
    {

        if (mobile == null || mobile.isBlank()) 
        {
            return "Mobile number is required";
        }

        if (userType == null || userType.isBlank()) 
        {
            return "User type is required";
        }

        userType = userType.toUpperCase();

        // -----------------------------------------------------
        // CHECK CUSTOMER
        // -----------------------------------------------------

        if ("CUSTOMER".equals(userType)) 
        {

            Optional<Customer> customer = customerRepository.findByMobile(mobile);

            if (customer.isEmpty()) 
            {
                return "Customer mobile number not found";
            }
        }

        // -----------------------------------------------------
        // CHECK ADMIN
        // -----------------------------------------------------

        else if ("ADMIN".equals(userType)) 
        {

            Optional<Admin> admin = adminRepository.findByMobile(mobile);

            if (admin.isEmpty()) 
            {
                return "Admin mobile number not found";
            }
        }

        else 
        {

            return "Invalid user type";
        }

        // -----------------------------------------------------
        // GENERATE 6 DIGIT OTP
        // -----------------------------------------------------

        int otpNumber = 100000 + secureRandom.nextInt(900000);

        String otp = String.valueOf(otpNumber);

        PasswordResetOtp resetOtp = new PasswordResetOtp();

        resetOtp.setMobile(mobile);

        resetOtp.setUserType(userType);

        resetOtp.setOtp(otp);

        // OTP valid for exactly 5 minutes
        resetOtp.setExpiryTime(
                LocalDateTime.now()
                        .plusMinutes(5));

        resetOtp.setVerified(false);

        otpRepository.save(resetOtp);

        // Development mode:
        // Later this will be sent through SMS.
        return otp;
    }

    // =========================================================
    // VERIFY OTP
    // =========================================================

    public String verifyOtp(
            String mobile,
            String userType,
            String otp) 
    {

        if (mobile == null || mobile.isBlank()) 
        {
            return "Mobile number is required";
        }

        if (userType == null || userType.isBlank()) 
        {
            return "User type is required";
        }

        if (otp == null || otp.isBlank()) 
        {
            return "OTP is required";
        }

        userType = userType.toUpperCase();

        Optional<PasswordResetOtp> optionalOtp =
                otpRepository
                        .findTopByMobileAndUserTypeOrderByIdDesc(
                                mobile,
                                userType);

        if (optionalOtp.isEmpty()) 
        {
            return "OTP not found";
        }

        PasswordResetOtp resetOtp = optionalOtp.get();

        // -----------------------------------------------------
        // CHECK ALREADY USED
        // -----------------------------------------------------

        if (resetOtp.isVerified()) 
        {
            return "OTP already used";
        }

        // -----------------------------------------------------
        // CHECK EXPIRY
        // -----------------------------------------------------

        if (LocalDateTime.now().isAfter(resetOtp.getExpiryTime())) 
        {

            return "OTP expired";
        }

        // -----------------------------------------------------
        // CHECK OTP
        // -----------------------------------------------------

        if (!resetOtp.getOtp().equals(otp)) 
        {
            return "Invalid OTP";
        }

        // -----------------------------------------------------
        // OTP VERIFIED
        // -----------------------------------------------------

        resetOtp.setVerified(true);

        otpRepository.save(resetOtp);

        return "OTP verified successfully";
    }

    // =========================================================
    // CHANGE CUSTOMER PASSWORD
    // =========================================================

    public String changeCustomerPassword(
            String mobile,
            String newPassword) 
    {

        Optional<Customer> optionalCustomer =customerRepository.findByMobile(mobile);

        if (optionalCustomer.isEmpty()) 
        {
            return "Customer not found";
        }

        Customer customer = optionalCustomer.get();

        customer.setPassword(newPassword);

        customerRepository.save(customer);

        return "Customer password changed successfully";
    }

    // =========================================================
    // CHANGE ADMIN PASSWORD
    // =========================================================

    public String changeAdminPassword(
            String mobile,
            String newPassword) 
    {

        Optional<Admin> optionalAdmin =  adminRepository.findByMobile(mobile);

        if (optionalAdmin.isEmpty()) 
        {
            return "Admin not found";
        }

        Admin admin = optionalAdmin.get();

        admin.setPassword(newPassword);

        adminRepository.save(admin);

        return "Admin password changed successfully";
    }
}