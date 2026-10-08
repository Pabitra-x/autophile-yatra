package com.autophile.yatra.controller;

public class RazorpayVerificationRequest {

    private Long bookingId;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;


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
    // RAZORPAY ORDER ID
    // =========================================================

    public String getRazorpayOrderId() 
    {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) 
    {

        this.razorpayOrderId = razorpayOrderId;
    }


    // =========================================================
    // RAZORPAY PAYMENT ID
    // =========================================================

    public String getRazorpayPaymentId() 
    {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) 
    {

        this.razorpayPaymentId = razorpayPaymentId;
    } 


    // =========================================================
    // RAZORPAY SIGNATURE
    // =========================================================

    public String getRazorpaySignature() 
    {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) 
    {

        this.razorpaySignature = razorpaySignature;
    }
}