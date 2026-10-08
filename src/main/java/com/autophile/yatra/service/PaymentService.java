package com.autophile.yatra.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.entity.Payment;
import com.autophile.yatra.repository.BookingRepository;
import com.autophile.yatra.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository) {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    // =========================================================
    // CREATE ADVANCE PAYMENT - 25%
    // =========================================================

    public Payment createAdvancePayment(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        // Advance payment only after admin approval
        if (!"APPROVED".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Advance payment is allowed only after admin approval");
        }

        // Check existing payment
        if (paymentRepository.findByBookingId(bookingId).isPresent()) {

            throw new RuntimeException(
                    "Payment already exists for this booking");
        }

        double totalAmount = booking.getTotalAmount();

        // 25% advance
        double advanceAmount = totalAmount * 0.25;

        // 75% remaining
        double remainingAmount = totalAmount * 0.75;

        Payment payment = new Payment();

        payment.setBookingId(bookingId);
        payment.setTotalAmount(totalAmount);
        payment.setAdvanceAmount(advanceAmount);
        payment.setRemainingAmount(remainingAmount);

        payment.setPaymentType("ADVANCE");
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // =========================================================
    // GET PAYMENT BY BOOKING ID
    // =========================================================

    public Payment getPaymentByBookingId(Long bookingId) {

        return paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found for this booking"));
    }

    // =========================================================
    // CREATE FINAL PAYMENT - 75%
    // =========================================================

    public Payment createFinalPayment(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        Payment payment = paymentRepository
                .findByBookingId(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Advance payment record not found"));

        // -----------------------------------------------------
        // CASE 1:
        // Final payment record was already created earlier.
        //
        // Example:
        // paymentType   = FINAL
        // paymentStatus = PENDING
        //
        // Allow user to retry Razorpay payment.
        // -----------------------------------------------------

        if ("FINAL".equalsIgnoreCase(
                payment.getPaymentType())) {

            if ("TRIP_STARTED".equalsIgnoreCase(
                    booking.getBookingStatus())) {

                double finalAmount =
                        booking.getTotalAmount() * 0.75;

                payment.setRemainingAmount(finalAmount);
                payment.setPaymentStatus("PENDING");

                return paymentRepository.save(payment);
            }

            throw new RuntimeException(
                    "Final payment is allowed only after trip starts");
        }

        // -----------------------------------------------------
        // CASE 2:
        // First time creating final payment.
        //
        // Trip must already be started.
        // -----------------------------------------------------

        if (!"TRIP_STARTED".equalsIgnoreCase(
                booking.getBookingStatus())) {

            throw new RuntimeException(
                    "Final payment is allowed only after trip starts");
        }

        // -----------------------------------------------------
        // Advance payment must have SUCCESS status
        // before converting ADVANCE -> FINAL.
        // -----------------------------------------------------

        if (!"SUCCESS".equalsIgnoreCase(
                payment.getPaymentStatus())) {

            throw new RuntimeException(
                    "Advance payment must be completed first");
        }

        double totalAmount =
                booking.getTotalAmount();

        double advanceAmount =
                totalAmount * 0.25;

        double finalAmount =
                totalAmount * 0.75;

        payment.setTotalAmount(totalAmount);
        payment.setAdvanceAmount(advanceAmount);
        payment.setRemainingAmount(finalAmount);

        payment.setPaymentType("FINAL");
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}