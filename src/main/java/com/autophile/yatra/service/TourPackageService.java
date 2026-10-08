package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.TourPackage;
import com.autophile.yatra.repository.TourPackageRepository;

@Service
public class TourPackageService 
{

    private final TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) 
    {

        this.tourPackageRepository =tourPackageRepository;
    }

    // =========================================================
    // GET ALL TOURS
    // =========================================================

    public List<TourPackage> getAllTours() 
    {

        return tourPackageRepository.findAll();
    }

    // =========================================================
    // GET TOUR BY ID
    // =========================================================

    public TourPackage getTourById(Long tourId) 
    {

        return tourPackageRepository.findById(tourId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour package not found"));
    }

    // =========================================================
    // ADD TOUR
    // =========================================================

    public TourPackage addTour(TourPackage tourPackage) 
    {

        return tourPackageRepository.save(tourPackage);
    }

    // =========================================================
    // UPDATE TOUR
    // =========================================================

    public TourPackage updateTour( Long tourId, TourPackage updatedTour) 
    {

        TourPackage existingTour =getTourById(tourId);

        existingTour.setName(updatedTour.getName());

        existingTour.setDurationDays(updatedTour.getDurationDays());

        existingTour.setTotalAmount(updatedTour.getTotalAmount());

        existingTour.setAvailableSlots(updatedTour.getAvailableSlots());

        existingTour.setImageUrl( updatedTour.getImageUrl());

        return tourPackageRepository.save(existingTour);
    }

    // =========================================================
    // DELETE TOUR
    // =========================================================

    public void deleteTour(Long tourId) 
    {

        if (!tourPackageRepository.existsById(tourId)) 
        {

            throw new RuntimeException("Tour package not found");
        }

        tourPackageRepository.deleteById(tourId);
    }
}