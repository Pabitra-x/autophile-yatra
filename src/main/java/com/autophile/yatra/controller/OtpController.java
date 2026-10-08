package com.autophile.yatra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.service.OtpService;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin
public class OtpController 
{

    private final OtpService otpService;

    public OtpController(OtpService otpService) 
    {
        this.otpService = otpService;
    }

    // =========================================================
    // GENERATE OTP
    // =========================================================

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String mobile) 
    {

        try 
        {

            String otp = otpService.generateOtp(mobile);

            // Development mode:
            // OTP is returned in response.
            // Later this will be replaced with real SMS sending.

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
    public ResponseEntity<String> verifyOtp(@RequestParam String mobile,@RequestParam String otp) 
    {

        try 
        {

            String result = otpService.verifyOtp(mobile,otp);

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