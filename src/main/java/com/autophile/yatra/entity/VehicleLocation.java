package com.autophile.yatra.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VehicleLocation 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private Long bookingId;

    private String vehicleNumber;

    private String driverName;

    private double latitude;

    private double longitude;

    private LocalDateTime updatedAt;


    // =========================================================
    // LOCATION ID
    // =========================================================

    public Long getLocationId() 
    {
        return locationId;
    }

    public void setLocationId(Long locationId) 
    {
        this.locationId = locationId;
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
    // VEHICLE NUMBER
    // =========================================================

    public String getVehicleNumber() 
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) 
    {
        this.vehicleNumber = vehicleNumber;
    }


    // =========================================================
    // DRIVER NAME
    // =========================================================

    public String getDriverName() 
    {
        return driverName;
    }

    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }


    // =========================================================
    // LATITUDE
    // =========================================================

    public double getLatitude() 
    {
        return latitude;
    }

    public void setLatitude(double latitude) 
    {
        this.latitude = latitude;
    }


    // =========================================================
    // LONGITUDE
    // =========================================================

    public double getLongitude() 
    {
        return longitude;
    }

    public void setLongitude(double longitude) 
    {
        this.longitude = longitude;
    }


    // =========================================================
    // UPDATED AT
    // =========================================================

    public LocalDateTime getUpdatedAt() 
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) 
    {
        this.updatedAt = updatedAt;
    }
}