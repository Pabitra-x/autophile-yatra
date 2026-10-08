package com.autophile.yatra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autophile.yatra.service.DriverPasswordResetService;

@RestController
@RequestMapping("/api/driver-password-reset")
@CrossOrigin
public class DriverPasswordResetController 
{

    private final DriverPasswordResetService passwordResetService;

    public DriverPasswordResetController(DriverPasswordResetService passwordResetService) 
    {

        this.passwordResetService = passwordResetService;
    }


    // -----------------------------------------
    // Generate OTP
    // -----------------------------------------

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestParam String mobile) 
    {

        try 
        {

            String result = passwordResetService.generateOtp(mobile);

            return ResponseEntity.ok(result);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // -----------------------------------------
    // Verify OTP
    // -----------------------------------------

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobile,@RequestParam String otp) 
    {

        try 
        {

            String result =passwordResetService.verifyOtp(mobile,otp);

            return ResponseEntity.ok(result);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // -----------------------------------------
    // Change Password
    // -----------------------------------------

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String mobile,@RequestParam String newPassword) 
    {

        try 
        {

            String result =passwordResetService.changePassword(mobile,newPassword);

            return ResponseEntity.ok(result);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}