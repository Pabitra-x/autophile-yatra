package com.autophile.yatra.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.VehicleLocation;
import com.autophile.yatra.repository.VehicleLocationRepository;

@Service
public class VehicleLocationService {

    private final VehicleLocationRepository vehicleLocationRepository;

    public VehicleLocationService(
            VehicleLocationRepository vehicleLocationRepository) {

        this.vehicleLocationRepository =
                vehicleLocationRepository;
    }

    // =========================================================
    // SAVE VEHICLE LOCATION
    // =========================================================

    public VehicleLocation saveLocation(
            Long bookingId,
            double latitude,
            double longitude,
            String vehicleNumber,
            String driverName) {

        if (bookingId == null) {
            throw new RuntimeException(
                    "Booking ID is required");
        }

        if (latitude < -90 || latitude > 90) {
            throw new RuntimeException(
                    "Invalid latitude");
        }

        if (longitude < -180 || longitude > 180) {
            throw new RuntimeException(
                    "Invalid longitude");
        }

        VehicleLocation location =
                new VehicleLocation();

        location.setBookingId(bookingId);

        location.setLatitude(latitude);

        location.setLongitude(longitude);

        location.setVehicleNumber(vehicleNumber);

        location.setDriverName(driverName);

        location.setUpdatedAt(
                LocalDateTime.now());

        return vehicleLocationRepository.save(
                location);
    }

    // =========================================================
    // GET LATEST VEHICLE LOCATION
    // =========================================================

    public Optional<VehicleLocation> getLatestLocation(
            Long bookingId) {

        return vehicleLocationRepository
                .findTopByBookingIdOrderByLocationIdDesc(
                        bookingId);
    }
}