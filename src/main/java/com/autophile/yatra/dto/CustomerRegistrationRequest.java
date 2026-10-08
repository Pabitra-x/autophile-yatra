package com.autophile.yatra.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class CustomerRegistrationRequest 
{

    @NotBlank
    private String name;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    private String password;


    // =========================================================
    // NAME
    // =========================================================

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }


    // =========================================================
    // DATE OF BIRTH
    // =========================================================

    public LocalDate getDateOfBirth() 
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }


    // =========================================================
    // EMAIL
    // =========================================================

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
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
}