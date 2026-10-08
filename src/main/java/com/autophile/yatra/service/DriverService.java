package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.entity.DriverAssignment;
import com.autophile.yatra.repository.DriverAssignmentRepository;
import com.autophile.yatra.repository.DriverRepository;

@Service
public class DriverService 
{

    private final DriverRepository driverRepository;
    private final DriverAssignmentRepository driverAssignmentRepository;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DriverService(DriverRepository driverRepository,DriverAssignmentRepository driverAssignmentRepository) 
    {

        this.driverRepository = driverRepository;
        this.driverAssignmentRepository = driverAssignmentRepository;
    }


    // =========================================================
    // DRIVER LOGIN
    // =========================================================

    public String login(String mobile,String password) 
    {

        if (mobile == null || mobile.isBlank())
        {
            throw new RuntimeException("Mobile number is required");
        }

        if (password == null || password.isBlank()) 
        {
            throw new RuntimeException("Password is required");
        }


        Driver driver = driverRepository
                .findByMobile(mobile)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));


        if (!driver.isActive()) 
        {

            throw new RuntimeException("Driver account is inactive");
        }


        if (!driver.getPassword().equals(password)) 
        {

            throw new RuntimeException("Invalid password");
        }


        return "Login successful|" + driver.getDriverId();
    }


    // =========================================================
    // GET DRIVER BY ID
    // =========================================================

    public Driver getDriverById(Long driverId) 
    {

        if (driverId == null) 
        {

            throw new RuntimeException("Driver ID is required");
        }


        return driverRepository.findById(driverId).orElseThrow(() ->new RuntimeException("Driver not found"));
    } 


    // =========================================================
    // GET ALL DRIVER ASSIGNMENTS
    // =========================================================

    public List<DriverAssignment> getDriverAssignments(Long driverId) 
    {

        if (driverId == null) 
        {

            throw new RuntimeException("Driver ID is required");
        }


        if (!driverRepository.existsById(driverId)) 
        {

            throw new RuntimeException("Driver not found");
        }


        return driverAssignmentRepository
                .findByDriverId(driverId);
    }


    // =========================================================
    // GET LATEST DRIVER ASSIGNMENT
    // =========================================================

    public DriverAssignment getLatestAssignment( Long driverId) 
    {

        if (driverId == null) 
        {

            throw new RuntimeException("Driver ID is required");
        }


        if (!driverRepository.existsById(driverId)) 
        {

            throw new RuntimeException("Driver not found");
        }


        return driverAssignmentRepository
                .findTopByDriverIdOrderByAssignmentIdDesc(driverId)
                .orElseThrow(() ->
                        new RuntimeException("No booking has been assigned to this driver"));
    }
}