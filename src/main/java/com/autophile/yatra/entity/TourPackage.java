package com.autophile.yatra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TourPackage 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;

    private String name;

    private int durationDays;

    private double totalAmount;

    private int availableSlots;

    private String imageUrl;


    // =========================================================
    // TOUR ID
    // =========================================================

    public Long getTourId() 
    {
        return tourId;
    }

    public void setTourId(Long tourId) 
    {
        this.tourId = tourId;
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
    // DURATION
    // =========================================================

    public int getDurationDays() 
    {
        return durationDays;
    }

    public void setDurationDays(int durationDays) 
    {
        this.durationDays = durationDays;
    }


    // =========================================================
    // TOTAL AMOUNT
    // =========================================================

    public double getTotalAmount() 
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }


    // =========================================================
    // AVAILABLE SLOTS
    // =========================================================

    public int getAvailableSlots() 
    {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) 
    {
        this.availableSlots = availableSlots;
    }


    // =========================================================
    // IMAGE URL
    // =========================================================

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }
}