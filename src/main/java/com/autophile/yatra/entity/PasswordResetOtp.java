package com.autophile.yatra.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PasswordResetOtp 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobile;

    private String userType;

    private String otp;

    private LocalDateTime expiryTime;

    private boolean verified;


    // =========================================================
    // ID
    // =========================================================

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
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
    // USER TYPE
    // =========================================================

    public String getUserType() 
    {
        return userType;
    }

    public void setUserType(String userType) 
    {
        this.userType = userType;
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