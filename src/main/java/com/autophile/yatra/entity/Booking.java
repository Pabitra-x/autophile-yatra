package com.autophile.yatra.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long customerId;

    private Long tourId;

    private LocalDate travelDate;

    private int numberOfPersons;

    private double totalAmount;

    private String bookingStatus;


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
    // TRAVEL DATE
    // =========================================================

    public LocalDate getTravelDate() 
    {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) 
    {
        this.travelDate = travelDate;
    }


    // =========================================================
    // NUMBER OF PERSONS
    // =========================================================

    public int getNumberOfPersons() 
    {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) 
    {
        this.numberOfPersons = numberOfPersons;
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
    // BOOKING STATUS
    // =========================================================

    public String getBookingStatus() 
    {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) 
    {
        this.bookingStatus = bookingStatus;
    }
}