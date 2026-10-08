package com.autophile.yatra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.VehicleLocation;

public interface VehicleLocationRepository
        extends JpaRepository<VehicleLocation, Long> 
{

    Optional<VehicleLocation>
    findTopByBookingIdOrderByLocationIdDesc(Long bookingId);
}