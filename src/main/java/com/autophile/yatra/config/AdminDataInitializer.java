package com.autophile.yatra.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.autophile.yatra.entity.Admin;
import com.autophile.yatra.repository.AdminRepository;

@Component
public class AdminDataInitializer implements CommandLineRunner 
{

    private final AdminRepository adminRepository;

    public AdminDataInitializer(AdminRepository adminRepository) 
    {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) 
    {

        if (adminRepository.count() > 0) 
        {
            return;
        }

        Admin admin = new Admin();

        admin.setUsername("pabitra");
        admin.setMobile("9861227074");
        admin.setPassword("YOUR_ADMIN_PASSWORD");
        admin.setActive(true);

        adminRepository.save(admin);

        System.out.println("=================================");
        System.out.println("Default Admin Created");
        System.out.println("Username: pabitra");
        System.out.println("Mobile: 9861227074");
        System.out.println("=================================");
    }
}