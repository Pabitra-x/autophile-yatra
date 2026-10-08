package com.autophile.yatra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> 
{

    Optional<Payment> findByBookingId(Long bookingId);

    List<Payment> findByPaymentStatus(String paymentStatus);
}