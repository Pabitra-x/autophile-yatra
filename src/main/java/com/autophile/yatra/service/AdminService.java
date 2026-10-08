package com.autophile.yatra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Admin;
import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.entity.Payment;
import com.autophile.yatra.repository.AdminRepository;
import com.autophile.yatra.repository.BookingRepository;
import com.autophile.yatra.repository.CustomerRepository;
import com.autophile.yatra.repository.PaymentRepository;

@Service
public class AdminService 
{

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public AdminService(AdminRepository adminRepository,CustomerRepository customerRepository,BookingRepository bookingRepository,PaymentRepository paymentRepository) 
    {

        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    public String loginAdmin(String username,String password) 
    {

        Optional<Admin> optionalAdmin =adminRepository.findByUsername(username);

        if (optionalAdmin.isEmpty()) 
        {
            return "Invalid username or password";
        }

        Admin admin = optionalAdmin.get();

        if (!admin.isActive()) 
        {
            return "Admin account is inactive";
        }

        if (!admin.getPassword().equals(password)) 
        {
            return "Invalid username or password";
        }

        return "Login successful|" + admin.getAdminId();
    }

    // =========================================================
    // TOTAL CUSTOMERS
    // =========================================================

    public long getTotalCustomers() 
    {
        return customerRepository.count();
    }

    // =========================================================
    // TOTAL BOOKINGS
    // =========================================================

    public long getTotalBookings() 
    {
        return bookingRepository.count();
    }

    // =========================================================
    // PENDING REQUESTS
    // =========================================================

    public long getPendingRequests() 
    {

        return bookingRepository
                .findByBookingStatus("PENDING")
                .size();
    }

    // =========================================================
    // APPROVED BOOKINGS
    // =========================================================

    public long getApprovedBookings() 
    {

        return bookingRepository
                .findByBookingStatus("APPROVED")
                .size();
    }

    // =========================================================
    // COMPLETED BOOKINGS
    // =========================================================

    public long getCompletedBookings() 
    {

        return bookingRepository
                .findByBookingStatus("COMPLETED")
                .size();
    }

    // =========================================================
    // TOTAL REVENUE
    // =========================================================

    public double getTotalRevenue() {

        List<Payment> successfulPayments =paymentRepository.findByPaymentStatus("SUCCESS");

        double revenue = 0;

        for (Payment payment :successfulPayments) 
        {

            if ("ADVANCE".equalsIgnoreCase(payment.getPaymentType())) 
            {

                revenue +=payment.getAdvanceAmount();

            } 
            else if ("FINAL".equalsIgnoreCase(payment.getPaymentType())) 
            {

                /*
                 * Final payment record contains
                 * remainingAmount = 0 after success.
                 *
                 * Therefore calculate the final
                 * 75% from total amount.
                 */

                revenue +=payment.getTotalAmount()* 0.75;
            }
        }

        return revenue;
    }
}