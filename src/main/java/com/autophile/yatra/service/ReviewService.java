package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Review;
import com.autophile.yatra.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getCustomerReviews(Long customerId) {
        return reviewRepository.findByCustomerIdOrderByReviewIdDesc(customerId);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByReviewIdDesc();
    }

    public List<Review> getBookingReviews(Long bookingId) {
        return reviewRepository.findByBookingId(bookingId);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}