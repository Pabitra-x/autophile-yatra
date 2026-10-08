package com.autophile.yatra.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
public class Customer 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank
    private String name;

    @Past
    private LocalDate dateOfBirth;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String mobile;

    @NotBlank
    private String password;


    // =========================================================
    // CUSTOMER ID
    // =========================================================

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }


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