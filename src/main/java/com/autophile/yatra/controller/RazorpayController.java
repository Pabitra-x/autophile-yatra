package com.autophile.yatra.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.Admin;
import com.autophile.yatra.entity.Booking;
import com.autophile.yatra.entity.Payment;
import com.autophile.yatra.repository.AdminRepository;
import com.autophile.yatra.repository.BookingRepository;
import com.autophile.yatra.repository.PaymentRepository;
import com.autophile.yatra.service.NotificationService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

@RestController
@RequestMapping("/api/razorpay")
@CrossOrigin
public class RazorpayController 
{

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final AdminRepository adminRepository;
    private final NotificationService notificationService;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public RazorpayController(PaymentRepository paymentRepository,BookingRepository bookingRepository,AdminRepository adminRepository,NotificationService notificationService) 
    {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.adminRepository = adminRepository;
        this.notificationService = notificationService;
    }

    // =========================================================
    // CREATE RAZORPAY ORDER
    // =========================================================

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam double amount) 
    {

        try 
        {

            if (amount <= 0) 
            {

                return ResponseEntity
                        .badRequest()
                        .body("Amount must be greater than zero");
            }

            int amountInPaise = (int) Math.round(amount * 100);

            RazorpayClient razorpayClient =new RazorpayClient(razorpayKeyId,razorpayKeySecret);

            JSONObject orderRequest = new JSONObject();

            orderRequest.put("amount",amountInPaise);

            orderRequest.put("currency","INR");

            orderRequest.put("receipt","AUTOPHILE_"+ System.currentTimeMillis());

            Order order = razorpayClient.orders.create(orderRequest);

            Map<String, Object> response = new HashMap<>();

            response.put("keyId",razorpayKeyId);

            response.put("orderId",order.get("id"));

            response.put("amount",amountInPaise);

            response.put( "currency", "INR");

            return ResponseEntity.ok(response);

        } 
        catch (Exception e) 
        {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .body("Unable to create Razorpay order: "+ e.getMessage());
        }
    }

    // =========================================================
    // VERIFY RAZORPAY PAYMENT
    // =========================================================

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody RazorpayVerificationRequest request) 
    {

        try 
        {

            // -------------------------------------------------
            // BASIC VALIDATION
            // -------------------------------------------------

            if (request == null
                    || request.getBookingId() == null
                    || request.getRazorpayOrderId() == null
                    || request.getRazorpayPaymentId() == null
                    || request.getRazorpaySignature() == null) {

                return ResponseEntity
                        .badRequest()
                        .body("Invalid payment verification data");
            }

            // -------------------------------------------------
            // VERIFY RAZORPAY SIGNATURE
            // -------------------------------------------------

            String generatedSignature =request.getRazorpayOrderId()+ "|"+ request.getRazorpayPaymentId();

            boolean signatureValid = Utils.verifySignature(generatedSignature,request.getRazorpaySignature(),razorpayKeySecret);

            if (!signatureValid) 
            {

                return ResponseEntity
                        .badRequest()
                        .body("Invalid Razorpay payment signature");
            }

            // -------------------------------------------------
            // FIND PAYMENT
            // -------------------------------------------------

            Payment payment = paymentRepository
                    .findByBookingId(
                            request.getBookingId())
                    .orElseThrow(() ->
                            new RuntimeException("Payment record not found"));

            // -------------------------------------------------
            // FIND BOOKING
            // -------------------------------------------------

            Booking booking =
                    bookingRepository
                    .findById(
                            request.getBookingId())
                    .orElseThrow(() ->
                            new RuntimeException( "Booking not found"));

            // =================================================
            // ADVANCE PAYMENT
            // =================================================

            if ("ADVANCE".equalsIgnoreCase(payment.getPaymentType()))
            {

                // Advance payment is allowed only
                // after admin approval.

                if (!"APPROVED".equalsIgnoreCase(booking.getBookingStatus())) 
                {

                    return ResponseEntity
                            .badRequest()
                            .body("Booking is not approved for advance payment");
                }

                // -------------------------------------------------
                // PAYMENT SUCCESS
                // -------------------------------------------------

                payment.setPaymentStatus("SUCCESS");

                payment.setRemainingAmount(booking.getTotalAmount() * 0.75);

                paymentRepository.save(payment);

                // -------------------------------------------------
                // BOOKING BECOMES ADVANCE_PAID
                // -------------------------------------------------

                booking.setBookingStatus("ADVANCE_PAID");

                bookingRepository.save(booking);

                // =================================================
                // ADMIN NOTIFICATION
                // =================================================

                for (Admin admin : adminRepository.findAll()) 
                {

                    if (admin.isActive()) 
                    {

                        notificationService.createAdminNotification(admin.getAdminId(),booking.getBookingId(),"Advance Payment Received","Advance payment of ₹"+ String.format("%.2f", payment.getAdvanceAmount())+ " has been successfully received "+ "for booking #"+ booking.getBookingId() + ".","ADVANCE_PAYMENT_SUCCESS");
                    }
                }

                Map<String, Object> response =new HashMap<>();

                response.put("message","Advance payment successful");

                response.put("bookingId",request.getBookingId());

                response.put("bookingStatus","ADVANCE_PAID");

                response.put("paymentStatus","SUCCESS");

                response.put("paymentType","ADVANCE");

                response.put("razorpayPaymentId",request.getRazorpayPaymentId());

                return ResponseEntity.ok(response);
            }

            // =================================================
            // FINAL PAYMENT
            // =================================================

            if ("FINAL".equalsIgnoreCase(payment.getPaymentType())) 
            {

                // Final payment is allowed only
                // after admin has started the trip.

                if (!"TRIP_STARTED".equalsIgnoreCase(booking.getBookingStatus())) 
                {

                    return ResponseEntity
                            .badRequest()
                            .body("Final payment is allowed only after trip starts");
                }

                /*
                 * IMPORTANT:
                 *
                 * createFinalPayment() changes the existing
                 * payment record to:
                 *
                 * paymentType   = FINAL
                 * paymentStatus = PENDING
                 *
                 * After Razorpay successfully verifies the
                 * final payment, we change it to SUCCESS.
                 */

                payment.setPaymentStatus("SUCCESS");

                payment.setRemainingAmount(0);

                paymentRepository.save(payment);

                // -------------------------------------------------
                // BOOKING BECOMES FULLY_PAID
                // -------------------------------------------------

                booking.setBookingStatus("FULLY_PAID");

                bookingRepository.save(booking);

                Map<String, Object> response =new HashMap<>();

                response.put("message","Final payment successful");

                response.put("bookingId",request.getBookingId());

                response.put("bookingStatus","FULLY_PAID");

                response.put("paymentStatus", "SUCCESS");

                response.put("paymentType","FINAL");

                response.put( "razorpayPaymentId", request.getRazorpayPaymentId());

                return ResponseEntity.ok(response);
            }

            // -------------------------------------------------
            // INVALID PAYMENT TYPE
            // -------------------------------------------------

            return ResponseEntity
                    .badRequest()
                    .body("Invalid payment type");

        } 
        catch (Exception e) 
        {

            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body("Payment verification failed: "+ e.getMessage());
        }
    }
}