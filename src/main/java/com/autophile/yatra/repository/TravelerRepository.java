package com.autophile.yatra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Traveler;

public interface TravelerRepository extends JpaRepository<Traveler, Long> 
{

    List<Traveler> findByBookingId(Long bookingId);
}