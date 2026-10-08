package com.autophile.yatra.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long bookingId;

    private double totalAmount;

    private double advanceAmount;

    private double remainingAmount;

    private String paymentType;

    private String paymentStatus;

    private LocalDateTime paymentDate;


    // =========================================================
    // PAYMENT ID
    // =========================================================

    public Long getPaymentId() 
    {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
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
    // ADVANCE AMOUNT
    // =========================================================

    public double getAdvanceAmount() 
    {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) 
    {
        this.advanceAmount = advanceAmount;
    }


    // =========================================================
    // REMAINING AMOUNT
    // =========================================================

    public double getRemainingAmount() 
    {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) 
    {
        this.remainingAmount = remainingAmount;
    }


    // =========================================================
    // PAYMENT TYPE
    // =========================================================

    public String getPaymentType() 
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType) 
    {
        this.paymentType = paymentType;
    }


    // =========================================================
    // PAYMENT STATUS
    // =========================================================

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }


    // =========================================================
    // PAYMENT DATE
    // =========================================================

    public LocalDateTime getPaymentDate() 
    {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) 
    {
        this.paymentDate = paymentDate;
    }
}