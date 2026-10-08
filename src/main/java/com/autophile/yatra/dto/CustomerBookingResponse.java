package com.autophile.yatra.dto;

import java.time.LocalDate;

public class CustomerBookingResponse 
{

    private Long bookingId;
    private Long customerId;
    private String customerName;
    private Long tourId;
    private LocalDate travelDate;
    private int numberOfPersons;
    private double totalAmount;
    private String bookingStatus;

    public CustomerBookingResponse() 
    {
    	
    }

    public CustomerBookingResponse(Long bookingId,Long customerId,String customerName,Long tourId,LocalDate travelDate,int numberOfPersons,double totalAmount,String bookingStatus) 
    {

        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.tourId = tourId;
        this.travelDate = travelDate;
        this.numberOfPersons = numberOfPersons;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
    }

    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public Long getTourId() 
    {
        return tourId;
    }

    public void setTourId(Long tourId) 
    {
        this.tourId = tourId;
    }

    public LocalDate getTravelDate() 
    {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) 
    {
        this.travelDate = travelDate;
    }

    public int getNumberOfPersons() 
    {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) 
    {
        this.numberOfPersons = numberOfPersons;
    }

    public double getTotalAmount() 
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public String getBookingStatus() 
    {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) 
    {
        this.bookingStatus = bookingStatus;
    }
}