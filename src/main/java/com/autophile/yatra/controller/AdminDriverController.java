package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.service.AdminDriverService;

@RestController
@RequestMapping("/api/admin/drivers")
@CrossOrigin
public class AdminDriverController 
{

    private final AdminDriverService adminDriverService;

    public AdminDriverController(AdminDriverService adminDriverService) 
    {

        this.adminDriverService = adminDriverService;
    }


    // =========================================================
    // ADD DRIVER
    // =========================================================

    @PostMapping("/add")
    public ResponseEntity<?> addDriver(@RequestParam String driverName,@RequestParam String mobile,@RequestParam String password) 
    {

        try 
        {

            Driver driver = adminDriverService.addDriver(driverName,mobile,password);

            return ResponseEntity.ok(driver);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET ALL DRIVERS
    // =========================================================

    @GetMapping
    public ResponseEntity<?> getAllDrivers() 
    {

        try 
        {

            List<Driver> drivers =adminDriverService.getAllDrivers();

            return ResponseEntity.ok(drivers);

        } catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET DRIVER BY ID
    // =========================================================

    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver( @PathVariable Long driverId) 
    {

        try 
        {

            Driver driver =adminDriverService.getDriverById(driverId);

            return ResponseEntity.ok(driver);

        } catch (RuntimeException e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    // =========================================================
    // UPDATE DRIVER
    // =========================================================

    @PutMapping("/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable Long driverId,@RequestParam String driverName,@RequestParam String mobile,@RequestParam String password) 
    {

        try 
        {

            Driver driver = adminDriverService.updateDriver(driverId,driverName,mobile,password);

            return ResponseEntity.ok(driver);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


   
    // ACTIVATE DRIVER
    
    @PutMapping("/{driverId}/activate")
    public ResponseEntity<?> activateDriver(@PathVariable Long driverId) 
    {

        try 
        {

            Driver driver =adminDriverService.activateDriver(driverId);

            return ResponseEntity.ok(driver);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // DEACTIVATE DRIVER
    // =========================================================

    @PutMapping("/{driverId}/deactivate")
    public ResponseEntity<?> deactivateDriver(
            @PathVariable Long driverId) {

        try {

            Driver driver =adminDriverService.deactivateDriver(driverId);

            return ResponseEntity.ok(driver);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // DELETE DRIVER
    // =========================================================

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long driverId) 
    {

        try 
        {

            String result =adminDriverService.deleteDriver(driverId);

            return ResponseEntity.ok(result);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}