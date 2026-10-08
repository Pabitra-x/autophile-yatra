package com.autophile.yatra.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.DriverAssignment;
import com.autophile.yatra.service.DriverAssignmentService;

@RestController
@RequestMapping("/api/driver-assignment")
@CrossOrigin
public class DriverAssignmentController 
{

    private final DriverAssignmentService assignmentService;

    public DriverAssignmentController(DriverAssignmentService assignmentService) 
    {

        this.assignmentService = assignmentService;
    }

    // =========================================================
    // ASSIGN DRIVER AND VEHICLE
    // =========================================================

    @PostMapping("/assign")
    public ResponseEntity<?> assignDriver(@RequestParam Long bookingId,@RequestParam String driverName,@RequestParam String vehicleNumber) 
    {

        try 
        {

            DriverAssignment assignment =assignmentService.assignDriver(bookingId,driverName,vehicleNumber);

            return ResponseEntity.ok(assignment);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // GET DRIVER ASSIGNMENT
    // =========================================================

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getAssignment(@PathVariable Long bookingId) 
    {

        try 
        {

            Optional<DriverAssignment> assignment =assignmentService.getAssignment(bookingId);

            if (assignment.isEmpty()) 
            {

                return ResponseEntity
                        .notFound()
                        .build();
            }

            return ResponseEntity.ok(assignment.get());

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // DELETE DRIVER ASSIGNMENT
    // =========================================================

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long bookingId) 
    {

        try 
        {

            assignmentService.deleteAssignment(bookingId);

            return ResponseEntity.ok("Driver assignment removed successfully");

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}