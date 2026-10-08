package com.autophile.yatra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autophile.yatra.service.PasswordResetService;

@RestController
@RequestMapping("/api/password-reset")
@CrossOrigin
public class PasswordResetController 
{

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) 
    {

        this.passwordResetService =passwordResetService;
    }

    // =========================================================
    // GENERATE OTP
    // =========================================================

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String mobile,@RequestParam String userType) 
    {

        try 
        {

            String otp =passwordResetService.generateOtp(mobile,userType);

            return ResponseEntity.ok("OTP generated: " + otp);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // VERIFY OTP
    // =========================================================

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String mobile,@RequestParam String userType,@RequestParam String otp) 
    {

        try 
        {

            String result =passwordResetService.verifyOtp(mobile,userType,otp);

            return ResponseEntity.ok(result);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // CHANGE CUSTOMER PASSWORD
    // =========================================================

    @PostMapping("/customer/change-password")
    public ResponseEntity<String> changeCustomerPassword( @RequestParam String mobile, @RequestParam String newPassword) 
    {

        try 
        {

            String result = passwordResetService.changeCustomerPassword(mobile,newPassword);

            return ResponseEntity.ok(result);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // CHANGE ADMIN PASSWORD
    // =========================================================

    @PostMapping("/admin/change-password")
    public ResponseEntity<String> changeAdminPassword(@RequestParam String mobile,@RequestParam String newPassword) 
    {

        try 
        {

            String result = passwordResetService.changeAdminPassword(mobile,newPassword);

            return ResponseEntity.ok(result);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}