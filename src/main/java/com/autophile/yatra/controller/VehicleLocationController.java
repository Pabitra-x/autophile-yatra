package com.autophile.yatra.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.VehicleLocation;
import com.autophile.yatra.service.VehicleLocationService;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin
public class VehicleLocationController 
{

    private final VehicleLocationService vehicleLocationService;

    public VehicleLocationController(VehicleLocationService vehicleLocationService) 
    {

        this.vehicleLocationService = vehicleLocationService;
    }

    // =========================================================
    // SAVE VEHICLE LOCATION
    // =========================================================

    @PostMapping("/location")
    public ResponseEntity<?> saveLocation(

            @RequestParam Long bookingId,

            @RequestParam double latitude,

            @RequestParam double longitude,

            @RequestParam(required = false)
            String vehicleNumber,

            @RequestParam(required = false)
            String driverName) 
    {

        try 
        {

            VehicleLocation location =vehicleLocationService.saveLocation(bookingId,latitude,longitude,vehicleNumber,driverName);

            return ResponseEntity.ok(location);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // GET LATEST VEHICLE LOCATION
    // =========================================================

    @GetMapping("/location/{bookingId}")
    public ResponseEntity<?> getLatestLocation(@PathVariable Long bookingId) 
    {

        try 
        {

            Optional<VehicleLocation> location = vehicleLocationService.getLatestLocation(bookingId);

            if (location.isEmpty()) 
            {

                return ResponseEntity
                        .notFound()
                        .build();
            }

            return ResponseEntity.ok(location.get());

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}