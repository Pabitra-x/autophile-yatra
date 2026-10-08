package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.entity.TourPackage;
import com.autophile.yatra.service.TourPackageService;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin
public class TourPackageController 
{

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) 
    {

        this.tourPackageService =tourPackageService;
    }

    // =========================================================
    // GET ALL TOURS
    // =========================================================

    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTours() 
    {

        return ResponseEntity.ok(tourPackageService.getAllTours());
    }

    // =========================================================
    // GET TOUR BY ID
    // =========================================================

    @GetMapping("/{tourId}")
    public ResponseEntity<?> getTourById(@PathVariable Long tourId) 
    {

        try 
        {

            return ResponseEntity.ok(tourPackageService.getTourById(tourId));

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // =========================================================
    // ADMIN - ADD TOUR
    // =========================================================

    @PostMapping
    public ResponseEntity<?> addTour(@RequestBody TourPackage tourPackage) 
    {

        try 
        {

            TourPackage savedTour =tourPackageService.addTour(tourPackage);

            return ResponseEntity.ok(savedTour);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - UPDATE TOUR
    // =========================================================

    @PutMapping("/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable Long tourId,@RequestBody TourPackage tourPackage) 
    {

        try 
        {

            TourPackage updatedTour =tourPackageService.updateTour(tourId,tourPackage);

            return ResponseEntity.ok(updatedTour);

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================================================
    // ADMIN - DELETE TOUR
    // =========================================================

    @DeleteMapping("/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable Long tourId) 
    {

        try 
        {

            tourPackageService.deleteTour(tourId);

            return ResponseEntity.ok("Tour deleted successfully");

        } 
        catch (Exception e) 
        {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}