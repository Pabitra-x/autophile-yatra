package com.autophile.yatra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig 
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
    {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Customer APIs
                .requestMatchers("/api/customers/**")
                .permitAll()

                // OTP APIs
                .requestMatchers("/api/otp/**")
                .permitAll()

                // Password reset APIs
                .requestMatchers("/api/password-reset/**")
                .permitAll()

                // Booking APIs
                .requestMatchers("/api/bookings/**")
                .permitAll()

                // Payment APIs
                .requestMatchers("/api/payments/**")
                .permitAll()

                // Razorpay APIs
                .requestMatchers("/api/razorpay/**")
                .permitAll()

                // Admin APIs
                .requestMatchers("/api/admin/**")
                .permitAll()

                // Tour APIs
                .requestMatchers("/api/tours/**")
                .permitAll()

                // Traveler APIs
                .requestMatchers("/api/travelers/**")
                .permitAll()

                // Tracking APIs
                .requestMatchers("/api/tracking/**")
                .permitAll()

                // Everything else
                .anyRequest()
                .permitAll()
            );

        return http.build();
    }
}