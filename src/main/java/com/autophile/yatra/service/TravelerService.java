package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Traveler;
import com.autophile.yatra.repository.TravelerRepository;

@Service
public class TravelerService 
{

    private final TravelerRepository travelerRepository;

    public TravelerService(TravelerRepository travelerRepository) 
    {

        this.travelerRepository =travelerRepository;
    }

    // =========================================================
    // SAVE ONE TRAVELER
    // =========================================================

    public Traveler saveTraveler(Traveler traveler) 
    {

        if (traveler.getBookingId() == null) 
        {

            throw new RuntimeException("Booking ID is required");
        }

        if (traveler.getName() == null ||traveler.getName().isBlank()) {

            throw new RuntimeException("Traveler name is required");
        }

        return travelerRepository.save(traveler);
    }

    // =========================================================
    // SAVE MULTIPLE TRAVELERS
    // =========================================================

    public List<Traveler> saveTravelers(
            List<Traveler> travelers) {

        if (travelers == null || travelers.isEmpty()) 
        {

            throw new RuntimeException("Traveler list cannot be empty");
        }

        for (Traveler traveler : travelers) 
        {

            if (traveler.getBookingId() == null) 
            {

                throw new RuntimeException("Booking ID is required");
            }

            if (traveler.getName() == null ||traveler.getName().isBlank()) 
            {

                throw new RuntimeException("Traveler name is required");
            }
        }

        return travelerRepository.saveAll(travelers);
    }

    // =========================================================
    // GET TRAVELERS BY BOOKING
    // =========================================================

    public List<Traveler> getTravelersByBookingId(Long bookingId) 
    {

        return travelerRepository.findByBookingId(bookingId);
    }
}