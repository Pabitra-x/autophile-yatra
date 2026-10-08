package com.autophile.yatra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String username;

    private String mobile;

    private String password;

    private boolean active = true;


    // =========================================================
    // ADMIN ID
    // =========================================================

    public Long getAdminId() 
    {
        return adminId;
    }

    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }


    // =========================================================
    // USERNAME
    // =========================================================

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
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
    // PASSWORD
    // =========================================================

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }


    // =========================================================
    // ACTIVE
    // =========================================================

    public boolean isActive() 
    {
        return active;
    }

    public void setActive(boolean active) 
    {
        this.active = active;
    }
}