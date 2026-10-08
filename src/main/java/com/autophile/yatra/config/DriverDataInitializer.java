package com.autophile.yatra.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.repository.DriverRepository;

@Component
public class DriverDataInitializer implements CommandLineRunner 
{

    private final DriverRepository driverRepository;

    public DriverDataInitializer(DriverRepository driverRepository) 
    {

        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args) 
    {

        // Create Deepak only if he does not already exist
        if (!driverRepository.existsByMobile("9876543210")) 
        {

            Driver driver = new Driver();

            driver.setDriverName("Deepak");
            driver.setMobile("9876543210");

            // Development/testing password
            driver.setPassword("Deepak@123");

            driver.setActive(true);

            driverRepository.save(driver);

            System.out.println("======================================");

            System.out.println("Driver account created successfully");

            System.out.println("Driver Name : Deepak");

            System.out.println("Mobile : 9876543210");

            System.out.println("Password    : Deepak@123");

            System.out.println("======================================");

        } 
        else 
        {

            System.out.println("Driver Deepak already exists.");
        }
    }
}