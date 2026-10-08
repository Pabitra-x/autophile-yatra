package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autophile.yatra.entity.Review;
import com.autophile.yatra.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController 
{

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) 
    {
        this.reviewService = reviewService;
    }

    // Add review
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) 
    {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    // Get reviews of a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable Long customerId) 
    {

        return ResponseEntity.ok(reviewService.getCustomerReviews(customerId) );
    }

    // Get all reviews - Admin
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() 
    {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // Get reviews of a booking
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Review>> getBookingReviews( @PathVariable Long bookingId) 
    {

        return ResponseEntity.ok( reviewService.getBookingReviews(bookingId));
    }

    // Get review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) 
    {

        Review review = reviewService.getReviewById(reviewId);

        if (review == null) 
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(review);
    }

    // Delete review - Admin
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId) 
    {

        Review review = reviewService.getReviewById(reviewId);

        if (review == null) 
        {
        	
            return ResponseEntity.notFound().build();
        }

        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok("Review deleted successfully");
    }
}