package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.entity.DriverAssignment;
import com.autophile.yatra.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin
public class DriverController 
{

    private final DriverService driverService;

    public DriverController(DriverService driverService) 
    {
        this.driverService = driverService;
    }


    // =========================================================
    // DRIVER LOGIN
    // =========================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String mobile,@RequestParam String password) 
    {

        try 
        {

            String result = driverService.login(mobile,password);

            return ResponseEntity.ok(result);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET DRIVER DETAILS
    // =========================================================

    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver(@PathVariable Long driverId) 
    {

        try 
        {

            Driver driver = driverService.getDriverById(driverId);

            return ResponseEntity.ok(driver);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    // =========================================================
    // GET ALL ASSIGNMENTS OF DRIVER
    // =========================================================

    @GetMapping("/{driverId}/assignments")
    public ResponseEntity<?> getAssignments(@PathVariable Long driverId) 
    {

        try {

            List<DriverAssignment> assignments = driverService.getDriverAssignments(driverId);

            return ResponseEntity.ok(assignments);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET LATEST ASSIGNMENT
    // =========================================================

    @GetMapping("/{driverId}/assignment")
    public ResponseEntity<?> getLatestAssignment(@PathVariable Long driverId) 
    {

        try 
        {

            DriverAssignment assignment = driverService.getLatestAssignment(driverId);

            return ResponseEntity.ok(assignment);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}