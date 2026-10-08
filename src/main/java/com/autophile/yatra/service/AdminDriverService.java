package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.repository.DriverRepository;

@Service
public class AdminDriverService 
{

    private final DriverRepository driverRepository;

    public AdminDriverService(DriverRepository driverRepository) 
    {
        this.driverRepository = driverRepository;
    }

    // =========================================================
    // ADD DRIVER
    // =========================================================

    public Driver addDriver(String driverName,String mobile,String password) {

        if (driverName == null || driverName.isBlank()) 
        {
            throw new RuntimeException("Driver name is required");
        }

        if (mobile == null || mobile.isBlank()) 
        {
            throw new RuntimeException("Mobile number is required");
        }

        if (!mobile.matches("\\d{10}")) 
        {
            throw new RuntimeException("Mobile number must contain 10 digits");
        }

        if (password == null || password.isBlank()) 
        {
            throw new RuntimeException("Password is required");
        }

        if (password.length() < 6) 
        {
            throw new RuntimeException("Password must contain at least 6 characters");
        }

        if (driverRepository.existsByMobile(mobile)) 
        {
            throw new RuntimeException("Driver with this mobile number already exists");
        }

        Driver driver = new Driver();

        driver.setDriverName(driverName.trim());
        driver.setMobile(mobile);
        driver.setPassword(password);
        driver.setActive(true);

        return driverRepository.save(driver);
    }


    // =========================================================
    // GET ALL DRIVERS
    // =========================================================

    public List<Driver> getAllDrivers() 
    {

        return driverRepository.findAll();
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

        return driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Driver not found"));
    }


    // =========================================================
    // UPDATE DRIVER
    // =========================================================

    public Driver updateDriver(
            Long driverId,
            String driverName,
            String mobile,
            String password) 
    {

        Driver driver = getDriverById(driverId);

        if (driverName == null || driverName.isBlank()) 
        {
            throw new RuntimeException("Driver name is required");
        }

        if (mobile == null || mobile.isBlank()) 
        {
            throw new RuntimeException("Mobile number is required");
        }

        if (!mobile.matches("\\d{10}")) 
        {
            throw new RuntimeException( "Mobile number must contain 10 digits");
        }

        if (password == null || password.isBlank()) 
        {
            throw new RuntimeException("Password is required");
        }

        if (password.length() < 6) 
        {
            throw new RuntimeException( "Password must contain at least 6 characters");
        }


        // Check whether another driver already
        // uses the same mobile number.

        Driver existingDriver =driverRepository.findByMobile(mobile)
                        .orElse(null);

        if (existingDriver != null&& !existingDriver.getDriverId() .equals(driverId)) {

            throw new RuntimeException("Another driver already uses this mobile number");
        }


        driver.setDriverName(driverName.trim());
        driver.setMobile(mobile);
        driver.setPassword(password);

        return driverRepository.save(driver);
    }


    // =========================================================
    // ACTIVATE DRIVER
    // =========================================================

    public Driver activateDriver(Long driverId) 
    {

        Driver driver = getDriverById(driverId);

        driver.setActive(true);

        return driverRepository.save(driver);
    }


    // =========================================================
    // DEACTIVATE DRIVER
    // =========================================================

    public Driver deactivateDriver(Long driverId) 
    {

        Driver driver = getDriverById(driverId);

        driver.setActive(false);

        return driverRepository.save(driver);
    }


    // =========================================================
    // DELETE DRIVER
    // =========================================================

    public String deleteDriver(Long driverId) 
    {

        Driver driver = getDriverById(driverId);

        driverRepository.delete(driver);

        return "Driver deleted successfully";
    }
}