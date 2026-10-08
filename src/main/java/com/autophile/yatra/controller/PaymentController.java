package com.autophile.yatra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.Payment;
import com.autophile.yatra.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController 
{

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) 
    {
        this.paymentService = paymentService;
    }

    // =========================================================
    // CREATE ADVANCE PAYMENT
    // =========================================================

    @PostMapping("/advance/{bookingId}")
    public ResponseEntity<?> createAdvancePayment(@PathVariable Long bookingId) 
    {

        try 
        {

            Payment payment =paymentService.createAdvancePayment(bookingId);

            return ResponseEntity.ok(payment);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // GET PAYMENT BY BOOKING ID
    // =========================================================

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getPayment(@PathVariable Long bookingId) 
    {

        try 
        {

            Payment payment = paymentService.getPaymentByBookingId(bookingId);

            return ResponseEntity.ok(payment);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // =========================================================
    // CREATE FINAL PAYMENT
    // =========================================================

    @PostMapping("/final/{bookingId}")
    public ResponseEntity<?> createFinalPayment(@PathVariable Long bookingId) 
    {

        try 
        {

            Payment payment =paymentService.createFinalPayment(bookingId);

            return ResponseEntity.ok(payment);

        } 
        catch (RuntimeException e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}