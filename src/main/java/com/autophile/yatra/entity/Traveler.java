package com.autophile.yatra.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Traveler 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelerId;

    private Long bookingId;

    private String name;

    private LocalDate dateOfBirth;

    private String mobile;

    private String email;

    private String gender;

    private String idProof;


    // =========================================================
    // TRAVELER ID
    // =========================================================

    public Long getTravelerId() 
    {
        return travelerId;
    }

    public void setTravelerId(Long travelerId) 
    {
        this.travelerId = travelerId;
    }


    // =========================================================
    // BOOKING ID
    // =========================================================

    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
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
    // GENDER
    // =========================================================

    public String getGender() 
    {
        return gender;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }


    // =========================================================
    // ID PROOF
    // =========================================================

    public String getIdProof() 
    {
        return idProof;
    }

    public void setIdProof(String idProof) 
    {
        this.idProof = idProof;
    }
}