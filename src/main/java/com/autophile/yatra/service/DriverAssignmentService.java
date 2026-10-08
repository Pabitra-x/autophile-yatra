package com.autophile.yatra.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.entity.Driver;
import com.autophile.yatra.entity.DriverAssignment;
import com.autophile.yatra.repository.BookingRepository;
import com.autophile.yatra.repository.DriverAssignmentRepository;
import com.autophile.yatra.repository.DriverRepository;

@Service
public class DriverAssignmentService 
{

    private final DriverAssignmentRepository assignmentRepository;
    private final DriverRepository driverRepository;
    private final BookingRepository bookingRepository;

    public DriverAssignmentService(
            DriverAssignmentRepository assignmentRepository,
            DriverRepository driverRepository,
            BookingRepository bookingRepository) 
    {

        this.assignmentRepository = assignmentRepository;
        this.driverRepository = driverRepository;
        this.bookingRepository = bookingRepository;
    }


    // =========================================================
    // ASSIGN DRIVER AND VEHICLE
    // =========================================================

    public DriverAssignment assignDriver(
            Long bookingId,
            String driverName,
            String vehicleNumber) 
    {

        // =====================================================
        // CHECK BOOKING ID
        // =====================================================

        if (bookingId == null) 
        {

            throw new RuntimeException("Booking ID is required");
        }


        // =====================================================
        // CHECK WHETHER BOOKING EXISTS
        // =====================================================

        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (booking.isEmpty()) 
        {

            throw new RuntimeException("Booking ID " + bookingId + " not found. "+ "Driver and vehicle were not assigned.");
        }


        // =====================================================
        // VALIDATE DRIVER NAME
        // =====================================================

        if (driverName == null ||
                driverName.isBlank()) 
        {

            throw new RuntimeException("Driver name is required");
        }


        // =====================================================
        // VALIDATE VEHICLE NUMBER
        // =====================================================

        if (vehicleNumber == null ||
                vehicleNumber.isBlank()) 
        {

            throw new RuntimeException("Vehicle number is required");
        }


        // =====================================================
        // FIND DRIVER
        // =====================================================

        Driver driver = driverRepository
                .findAll()
                .stream()
                .filter(d ->
                        d.getDriverName()
                         .equalsIgnoreCase(driverName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Driver not found: "
                                + driverName));


        // =====================================================
        // CHECK DRIVER ACTIVE
        // =====================================================

        if (!driver.isActive()) 
        {

            throw new RuntimeException("Driver account is inactive");
        }


        // =====================================================
        // CHECK EXISTING ASSIGNMENT
        // =====================================================

        Optional<DriverAssignment> existing = assignmentRepository.findByBookingId(bookingId);

        DriverAssignment assignment;


        if (existing.isPresent()) 
        {

            assignment = existing.get();

        } 
        else 
        {

            assignment = new DriverAssignment();

            assignment.setBookingId(bookingId);
        }


        // =====================================================
        // SET DRIVER DETAILS
        // =====================================================

        assignment.setDriverId(driver.getDriverId());

        assignment.setDriverName(driver.getDriverName());

        assignment.setVehicleNumber(vehicleNumber);


        // =====================================================
        // SAVE ASSIGNMENT
        // =====================================================

        return assignmentRepository.save(assignment);
    }


    // =========================================================
    // GET DRIVER ASSIGNMENT
    // =========================================================

    public Optional<DriverAssignment> getAssignment(Long bookingId) 
    {

        return assignmentRepository.findByBookingId(bookingId);
    }


    // =========================================================
    // DELETE DRIVER ASSIGNMENT
    // =========================================================

    public void deleteAssignment(Long bookingId) 
    {

        Optional<DriverAssignment> existing =assignmentRepository .findByBookingId(bookingId);

        if (existing.isEmpty()) 
        {

            throw new RuntimeException("Driver assignment not found");
        }

        assignmentRepository.delete(existing.get());
    }
}