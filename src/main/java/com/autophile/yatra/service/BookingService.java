package com.autophile.yatra.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Admin;
import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.entity.TourPackage;
import com.autophile.yatra.repository.AdminRepository;
import com.autophile.yatra.repository.BookingRepository;
import com.autophile.yatra.repository.TourPackageRepository;

@Service
public class BookingService 
{

    private final BookingRepository bookingRepository;
    private final TourPackageRepository tourPackageRepository;
    private final NotificationService notificationService;
    private final AdminRepository adminRepository;

    public BookingService(
            BookingRepository bookingRepository,
            TourPackageRepository tourPackageRepository,
            NotificationService notificationService,
            AdminRepository adminRepository) 
    {

        this.bookingRepository = bookingRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.notificationService = notificationService;
        this.adminRepository = adminRepository;
    }

    // =========================================================
    // CREATE BOOKING
    // =========================================================

    public Booking createBooking(
            Long customerId,
            Long tourId,
            LocalDate travelDate,
            int numberOfPersons) 
    {

        if (numberOfPersons <= 0)
        {

            throw new RuntimeException("Number of persons must be greater than zero");
        }

        TourPackage tour =
                tourPackageRepository.findById(tourId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour package not found"));

        if (tour.getAvailableSlots() < numberOfPersons) 
        {

            throw new RuntimeException("Not enough slots available");
        }

        double totalAmount =tour.getTotalAmount()* numberOfPersons;

        Booking booking = new Booking();

        booking.setCustomerId(customerId);

        booking.setTourId(tourId);

        booking.setTravelDate( travelDate);

        booking.setNumberOfPersons( numberOfPersons);

        booking.setTotalAmount(totalAmount);

        // New booking waits for admin approval
        booking.setBookingStatus("PENDING");

        // Reserve slots
        tour.setAvailableSlots(
                tour.getAvailableSlots()
                - numberOfPersons);

        tourPackageRepository.save( tour);

        Booking savedBooking =bookingRepository.save( booking);

        // =====================================================
        // CUSTOMER NOTIFICATION
        // =====================================================

        notificationService.createCustomerNotification(

                customerId,

                savedBooking.getBookingId(),

                "Booking Submitted",

                "Your booking #"
                + savedBooking.getBookingId()
                + " has been submitted successfully. "
                + "Please wait for admin approval.",

                "BOOKING"

        );

        // =====================================================
        // ADMIN NOTIFICATION
        // =====================================================

        List<Admin> admins =
                adminRepository.findAll();

        for (Admin admin : admins) {

            if (admin.isActive()) {

                notificationService.createAdminNotification(

                        admin.getAdminId(),

                        savedBooking.getBookingId(),

                        "New Booking Request",

                        "A new booking request #"
                        + savedBooking.getBookingId()
                        + " has been received. "
                        + "Please review the booking.",

                        "NEW_BOOKING"

                );
            }
        }

        return savedBooking;
    }

    // =========================================================
    // GET CUSTOMER BOOKINGS
    // =========================================================

    public List<Booking> getBookingsByCustomer(
            Long customerId) {

        return bookingRepository.findByCustomerId(
                customerId);
    }

    // =========================================================
    // GET BOOKING BY ID
    // =========================================================

    public Booking getBookingById(
            Long bookingId) {

        return bookingRepository.findById(
                bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Booking not found"));
    }

    // =========================================================
    // ADMIN - GET PENDING BOOKINGS
    // =========================================================

    public List<Booking> getPendingBookings() {

        return bookingRepository
                .findByBookingStatusOrderByBookingIdDesc(
                        "PENDING");
    }

    // =========================================================
    // ADMIN - APPROVE BOOKING
    // =========================================================

    public Booking approveBooking(
            Long bookingId) {

        Booking booking =
                getBookingById(
                        bookingId);

        if (!"PENDING".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Only PENDING bookings can be approved");
        }

        // Change booking status
        booking.setBookingStatus(
                "APPROVED");

        // Save booking
        Booking savedBooking =
                bookingRepository.save(
                        booking);

        // =====================================================
        // CUSTOMER NOTIFICATION
        // =====================================================

        notificationService.createCustomerNotification(

                savedBooking.getCustomerId(),

                savedBooking.getBookingId(),

                "Booking Approved",

                "Good news! Your booking #"
                + savedBooking.getBookingId()
                + " has been approved by the admin. "
                + "You can now make the 25% advance payment.",

                "BOOKING_APPROVED"

        );

        return savedBooking;
    }

    // =========================================================
    // ADMIN - REJECT BOOKING
    // =========================================================

    public Booking rejectBooking(
            Long bookingId) {

        Booking booking =
                getBookingById(
                        bookingId);

        if (!"PENDING".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Only PENDING bookings can be rejected");
        }

        // Change status
        booking.setBookingStatus(
                "REJECTED");

        // Save booking
        Booking savedBooking =
                bookingRepository.save(
                        booking);

        // =====================================================
        // CUSTOMER NOTIFICATION
        // =====================================================

        notificationService.createCustomerNotification(

                savedBooking.getCustomerId(),

                savedBooking.getBookingId(),

                "Booking Rejected",

                "Unfortunately, your booking #"
                + savedBooking.getBookingId()
                + " has been rejected by the admin.",

                "BOOKING_REJECTED"

        );

        return savedBooking;
    }

    // =========================================================
    // GET BOOKINGS BY STATUS
    // =========================================================

    public List<Booking> getBookingsByStatus(
            String status) {

        return bookingRepository.findByBookingStatus(
                status);
    }

    // =========================================================
    // ADMIN - START TRIP
    // =========================================================

    public Booking startTrip(
            Long bookingId) {

        Booking booking =
                getBookingById(
                        bookingId);

        if (!"ADVANCE_PAID".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Trip can start only after advance payment");
        }

        // Change status
        booking.setBookingStatus(
                "TRIP_STARTED");

        // Save booking
        Booking savedBooking =
                bookingRepository.save(
                        booking);

        // =====================================================
        // CUSTOMER NOTIFICATION
        // =====================================================

        notificationService.createCustomerNotification(

                savedBooking.getCustomerId(),

                savedBooking.getBookingId(),

                "Trip Started",

                "Your trip for booking #"
                + savedBooking.getBookingId()
                + " has started. "
                + "You can now use Live Trip Tracking.",

                "TRIP_STARTED"

        );

        return savedBooking;
    }

    // =========================================================
    // ADMIN - COMPLETE TRIP
    // =========================================================

    public Booking completeTrip(
            Long bookingId) {

        Booking booking =
                getBookingById(
                        bookingId);

        if (!"FULLY_PAID".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Trip can be completed only after full payment");
        }

        // Change status
        booking.setBookingStatus(
                "COMPLETED");

        // Save booking
        Booking savedBooking =
                bookingRepository.save(
                        booking);

        // =====================================================
        // CUSTOMER NOTIFICATION
        // =====================================================

        notificationService.createCustomerNotification(

                savedBooking.getCustomerId(),

                savedBooking.getBookingId(),

                "Trip Completed",

                "Your trip for booking #"
                + savedBooking.getBookingId()
                + " has been completed successfully. "
                + "Thank you for travelling with Autophile Yatra.",

                "TRIP_COMPLETED"

        );

        return savedBooking;
    }
}