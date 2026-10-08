package com.autophile.yatra.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.autophile.yatra.entity.TourPackage;
import com.autophile.yatra.repository.TourPackageRepository;

@Component
public class DataInitializer implements CommandLineRunner 
{

    private final TourPackageRepository tourPackageRepository;

    public DataInitializer(TourPackageRepository tourPackageRepository) 
    {

        this.tourPackageRepository =tourPackageRepository;
    }

    @Override
    public void run(String... args) 
    {

        // Do not insert duplicate tour data
        if (tourPackageRepository.count() > 0) 
        {
            return;
        }

        // =====================================================
        // GOA
        // =====================================================

        TourPackage goa = new TourPackage();

        goa.setName("Goa Beach Holiday");
        goa.setDurationDays(4);
        goa.setTotalAmount(15000);
        goa.setAvailableSlots(20);
        goa.setImageUrl("/images/goa.jpg");

        tourPackageRepository.save(goa);


        // =====================================================
        // KERALA
        // =====================================================

        TourPackage kerala = new TourPackage();

        kerala.setName("Kerala Nature Tour");
        kerala.setDurationDays(5);
        kerala.setTotalAmount(18000);
        kerala.setAvailableSlots(15);
        kerala.setImageUrl("/images/kerala.jpg");

        tourPackageRepository.save(kerala);


        // =====================================================
        // MANALI
        // =====================================================

        TourPackage manali = new TourPackage();

        manali.setName("Manali Mountain Adventure");
        manali.setDurationDays(6);
        manali.setTotalAmount(22000);
        manali.setAvailableSlots(12);
        manali.setImageUrl("/images/manali.jpg");

        tourPackageRepository.save(manali);


        // =====================================================
        // RAJASTHAN
        // =====================================================

        TourPackage rajasthan = new TourPackage();

        rajasthan.setName("Rajasthan Heritage Tour");
        rajasthan.setDurationDays(5);
        rajasthan.setTotalAmount(20000);
        rajasthan.setAvailableSlots(18);
        rajasthan.setImageUrl("/images/rajasthan.jpg");

        tourPackageRepository.save(rajasthan);


        System.out.println(
                "==========================================");

        System.out.println(
                "Tour packages initialized successfully");

        System.out.println(
                "==========================================");
    }
}