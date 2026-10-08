package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.Traveler;
import com.autophile.yatra.service.TravelerService;

@RestController
@RequestMapping("/api/travelers")
@CrossOrigin
public class TravelerController 
{

    private final TravelerService travelerService;

    public TravelerController(TravelerService travelerService) 
    {

        this.travelerService = travelerService;
    }


    // =========================================================
    // SAVE ONE TRAVELER
    // POST /api/travelers
    // =========================================================

    @PostMapping
    public ResponseEntity<?> saveTraveler(@RequestBody Traveler traveler) 
    {

        try 
        {

            Traveler savedTraveler = travelerService.saveTraveler(traveler);

            return ResponseEntity.ok(savedTraveler);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // SAVE MULTIPLE TRAVELERS
    // POST /api/travelers/multiple
    // =========================================================

    @PostMapping("/multiple")
    public ResponseEntity<?> saveTravelers(@RequestBody List<Traveler> travelers) 
    {

        try 
        {

            List<Traveler> savedTravelers = travelerService.saveTravelers(travelers);

            return ResponseEntity.ok(savedTravelers);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // SAVE MULTIPLE TRAVELERS FOR A BOOKING
    //
    // POST /api/travelers/booking/{bookingId}
    // =========================================================

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<?> saveTravelersForBooking(@PathVariable Long bookingId,@RequestBody List<Traveler> travelers) 
    {

        try 
        {

            if (bookingId == null) 
            {

                return ResponseEntity
                        .badRequest()
                        .body("Booking ID is required.");
            }


            if (travelers == null || travelers.isEmpty()) 
            {

                return ResponseEntity
                        .badRequest()
                        .body("Traveler details are required.");
            }


            /*
             * Set bookingId for every traveler.
             *
             * This is important because the frontend
             * sends traveler details without bookingId.
             */

            for (Traveler traveler : travelers) 
            {

                traveler.setBookingId(bookingId);
            }


            /*
             * Save all travelers.
             */

            List<Traveler> savedTravelers = travelerService.saveTravelers(travelers);


            return ResponseEntity.ok(savedTravelers);

        } catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET TRAVELERS BY BOOKING ID
    //
    // GET /api/travelers/booking/{bookingId}
    // =========================================================

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getTravelers(@PathVariable Long bookingId) 
    {

        try 
        {

            List<Traveler> travelers = travelerService.getTravelersByBookingId(bookingId);

            return ResponseEntity.ok(travelers);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}