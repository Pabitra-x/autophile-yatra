package com.autophile.yatra.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController 
{

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) 
    {
        this.bookingService = bookingService;
    }

    // =========================================================
    // CREATE BOOKING
    // =========================================================

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestParam Long customerId,@RequestParam Long tourId,@RequestParam String travelDate,@RequestParam int numberOfPersons) 
    {

        try 
        {

            LocalDate date =LocalDate.parse(travelDate);

            Booking booking =bookingService.createBooking(customerId,tourId,date,numberOfPersons);

            return ResponseEntity.ok(booking);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // CUSTOMER BOOKINGS
    // =========================================================

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerBookings(
            @PathVariable Long customerId) {

        try 
        {

            List<Booking> bookings =bookingService.getBookingsByCustomer(customerId);

            return ResponseEntity.ok(bookings);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // GET BOOKING BY ID
    // =========================================================

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBooking(
            @PathVariable Long bookingId) {

        try {

            Booking booking =bookingService.getBookingById(bookingId);

            return ResponseEntity.ok(booking);

        } catch (Exception e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // =========================================================
    // ADMIN - PENDING BOOKINGS
    // =========================================================

    @GetMapping("/admin/pending")
    public ResponseEntity<?> getPendingBookings() 
    {

        try 
        {

            return ResponseEntity.ok(bookingService.getPendingBookings());

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - APPROVE BOOKING
    // =========================================================

    @PutMapping("/admin/approve/{bookingId}")
    public ResponseEntity<?> approveBooking(@PathVariable Long bookingId) 
    {

        try 
        {

            Booking booking =bookingService.approveBooking(bookingId);

            return ResponseEntity.ok(booking);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - REJECT BOOKING
    // =========================================================

    @PutMapping("/admin/reject/{bookingId}")
    public ResponseEntity<?> rejectBooking(
            @PathVariable Long bookingId) {

        try {

            Booking booking =
                    bookingService.rejectBooking(
                            bookingId);

            return ResponseEntity.ok(booking);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - ADVANCE PAID BOOKINGS
    // =========================================================

    @GetMapping("/admin/advance-paid")
    public ResponseEntity<?> getAdvancePaidBookings() {

        try {

            return ResponseEntity.ok(
                    bookingService.getBookingsByStatus(
                            "ADVANCE_PAID"));

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - START TRIP
    // =========================================================

    @PutMapping("/admin/start-trip/{bookingId}")
    public ResponseEntity<?> startTrip(
            @PathVariable Long bookingId) {

        try {

            Booking booking =
                    bookingService.startTrip(
                            bookingId);

            return ResponseEntity.ok(booking);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - TRIP STARTED BOOKINGS
    // =========================================================

    @GetMapping("/admin/trip-started")
    public ResponseEntity<?> getTripStartedBookings() {

        try {

            return ResponseEntity.ok(
                    bookingService.getBookingsByStatus(
                            "TRIP_STARTED"));

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - COMPLETE TRIP
    // =========================================================

    @PutMapping("/admin/complete-trip/{bookingId}")
    public ResponseEntity<?> completeTrip(
            @PathVariable Long bookingId) {

        try {

            Booking booking =
                    bookingService.completeTrip(
                            bookingId);

            return ResponseEntity.ok(booking);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}