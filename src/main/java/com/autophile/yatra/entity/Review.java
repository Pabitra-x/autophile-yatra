package com.autophile.yatra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long customerId;

    private Long bookingId;

    private int rating;

    @Column(name = "review_comment")
    private String comment;

    // Default constructor
    public Review() 
    {
    	
    }

    // Review ID
    public Long getReviewId() 
    {
        return reviewId;
    }

    public void setReviewId(Long reviewId) 
    {
        this.reviewId = reviewId;
    }

    // Customer ID
    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    // Booking ID
    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
    }

    // Rating
    public int getRating() 
    {
        return rating;
    }

    public void setRating(int rating) 
    {
        this.rating = rating;
    }

    // Comment
    public String getComment() 
    {
        return comment;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }
}