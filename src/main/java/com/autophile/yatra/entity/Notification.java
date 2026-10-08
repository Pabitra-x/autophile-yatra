package com.autophile.yatra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long customerId;

    private Long bookingId;

    private String recipientType;

    private Long recipientId;

    private String title;

    private String message;

    private String notificationType;

    private boolean readStatus;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================

    public Notification() 
    {
    	
    }

    // =========================
    // CUSTOMER NOTIFICATION
    // =========================

    public Notification(
            Long customerId,
            Long bookingId,
            String title,
            String message,
            String notificationType) 
    {

        this.customerId = customerId;
        this.bookingId = bookingId;
        this.recipientType = "CUSTOMER";
        this.recipientId = customerId;
        this.title = title;
        this.message = message;
        this.notificationType = notificationType;
        this.readStatus = false;
    }

    // =========================
    // GENERAL NOTIFICATION
    // =========================

    public Notification(
            String recipientType,
            Long recipientId,
            Long customerId,
            Long bookingId,
            String title,
            String message,
            String notificationType) 
    {

        this.recipientType = recipientType;
        this.recipientId = recipientId;
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.title = title;
        this.message = message;
        this.notificationType = notificationType;
        this.readStatus = false;
    }

    // =========================
    // GETTERS AND SETTERS
    // =========================

    public Long getNotificationId() 
    {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) 
    {
        this.notificationId = notificationId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
    }

    public String getRecipientType() 
    {
        return recipientType;
    }

    public void setRecipientType(String recipientType) 
    {
        this.recipientType = recipientType;
    }

    public Long getRecipientId() 
    {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) 
    {
        this.recipientId = recipientId;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getNotificationType() 
    {
        return notificationType;
    }

    public void setNotificationType(String notificationType) 
    {
        this.notificationType = notificationType;
    }

    public boolean isReadStatus() 
    {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) 
    {
        this.readStatus = readStatus;
    }
}