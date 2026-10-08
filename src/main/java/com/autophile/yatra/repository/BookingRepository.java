package com.autophile.yatra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Booking;

public interface BookingRepository
        extends JpaRepository<Booking, Long> 
{

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByBookingStatus(String bookingStatus);

    List<Booking> findByBookingStatusOrderByBookingIdDesc(String bookingStatus);
}