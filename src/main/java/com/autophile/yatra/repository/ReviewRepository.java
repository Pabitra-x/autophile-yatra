package com.autophile.yatra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> 
{

    List<Review> findByCustomerIdOrderByReviewIdDesc(Long customerId);

    List<Review> findAllByOrderByReviewIdDesc();

    List<Review> findByBookingId(Long bookingId);
}