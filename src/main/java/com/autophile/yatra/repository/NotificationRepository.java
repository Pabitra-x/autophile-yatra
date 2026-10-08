package com.autophile.yatra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> 
{

    // =========================
    // CUSTOMER NOTIFICATIONS
    // =========================

    List<Notification> findByCustomerIdOrderByNotificationIdDesc(Long customerId);

    List<Notification> findByCustomerIdAndReadStatusFalseOrderByNotificationIdDesc(Long customerId);

    long countByCustomerIdAndReadStatusFalse(Long customerId);

    // =========================
    // ALL NOTIFICATIONS
    // =========================

    List<Notification> findAllByOrderByNotificationIdDesc();

    // =========================
    // ADMIN NOTIFICATIONS
    // =========================

    List<Notification> findByRecipientTypeAndRecipientIdOrderByNotificationIdDesc(String recipientType,Long recipientId);

    List<Notification> findByRecipientTypeAndRecipientIdAndReadStatusFalseOrderByNotificationIdDesc(String recipientType,Long recipientId);

    long countByRecipientTypeAndRecipientIdAndReadStatusFalse(String recipientType,Long recipientId);
}