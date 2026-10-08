package com.autophile.yatra.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OtpVerification 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpId;

    private String mobile;

    private String otp;

    private LocalDateTime expiryTime;

    private boolean verified;


    // =========================================================
    // OTP ID
    // =========================================================

    public Long getOtpId() 
    {
        return otpId;
    }

    public void setOtpId(Long otpId) 
    {
        this.otpId = otpId;
    }


    // =========================================================
    // MOBILE
    // =========================================================

    public String getMobile() 
    {
        return mobile;
    }

    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }


    // =========================================================
    // OTP
    // =========================================================

    public String getOtp() 
    {
        return otp;
    }

    public void setOtp(String otp) 
    {
        this.otp = otp;
    }


    // =========================================================
    // EXPIRY TIME
    // =========================================================

    public LocalDateTime getExpiryTime() 
    {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) 
    {
        this.expiryTime = expiryTime;
    }


    // =========================================================
    // VERIFIED
    // =========================================================

    public boolean isVerified() 
    {
        return verified;
    }

    public void setVerified(boolean verified) 
    {
        this.verified = verified;
    }
}