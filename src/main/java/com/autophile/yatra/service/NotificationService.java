package com.autophile.yatra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.autophile.yatra.entity.Notification;
import com.autophile.yatra.repository.NotificationRepository;

@Service
public class NotificationService 
{

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) 
    {

        this.notificationRepository = notificationRepository;
    }

    // =====================================================
    // CREATE CUSTOMER NOTIFICATION
    // =====================================================

    public Notification createNotification(
            Long customerId,
            Long bookingId,
            String title,
            String message,
            String notificationType) 
    {

        Notification notification =new Notification(customerId,bookingId,title,message,notificationType);

        return notificationRepository.save(notification);
    }

    // =====================================================
    // CREATE ADMIN NOTIFICATION
    // =====================================================

    public Notification createAdminNotification(
            Long adminId,
            Long bookingId,
            String title,
            String message,
            String notificationType) 
    {

        Notification notification =
                new Notification(
                        "ADMIN",
                        adminId,
                        null,
                        bookingId,
                        title,
                        message,
                        notificationType);

        return notificationRepository.save(notification);
    }

    // =====================================================
    // CREATE CUSTOMER NOTIFICATION
    // EXPLICIT VERSION
    // =====================================================

    public Notification createCustomerNotification(
            Long customerId,
            Long bookingId,
            String title,
            String message,
            String notificationType) 
    {

        Notification notification =
                new Notification(
                        "CUSTOMER",
                        customerId,
                        customerId,
                        bookingId,
                        title,
                        message,
                        notificationType);

        return notificationRepository.save(notification);
    }

    // =====================================================
    // GET CUSTOMER NOTIFICATIONS
    // =====================================================

    public List<Notification> getCustomerNotifications(Long customerId) 
    {

        return notificationRepository
                .findByCustomerIdOrderByNotificationIdDesc(customerId );
    }

    // =====================================================
    // GET CUSTOMER UNREAD NOTIFICATIONS
    // =====================================================

    public List<Notification> getUnreadCustomerNotifications(Long customerId) 
    {

        return notificationRepository
                .findByCustomerIdAndReadStatusFalseOrderByNotificationIdDesc(
                        customerId );
    }

    // =====================================================
    // GET CUSTOMER UNREAD COUNT
    // =====================================================

    public long getCustomerUnreadCount(Long customerId) 
    {

        return notificationRepository
                .countByCustomerIdAndReadStatusFalse(
                        customerId);
    }

    // =====================================================
    // GET ADMIN NOTIFICATIONS
    // =====================================================

    public List<Notification> getAdminNotifications(Long adminId) 
    {

        return notificationRepository
                .findByRecipientTypeAndRecipientIdOrderByNotificationIdDesc(
                        "ADMIN",
                        adminId);
    }

    // =====================================================
    // GET ADMIN UNREAD NOTIFICATIONS
    // =====================================================

    public List<Notification> getUnreadAdminNotifications(Long adminId) 
    {

        return notificationRepository
                .findByRecipientTypeAndRecipientIdAndReadStatusFalseOrderByNotificationIdDesc(
                        "ADMIN",
                        adminId);
    }

    // =====================================================
    // GET ADMIN UNREAD COUNT
    // =====================================================

    public long getAdminUnreadCount(Long adminId) 
    {

        return notificationRepository
                .countByRecipientTypeAndRecipientIdAndReadStatusFalse(
                        "ADMIN",
                        adminId);
    }

    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    public List<Notification> getAllNotifications() 
    {

        return notificationRepository.findAllByOrderByNotificationIdDesc();
    }

    // =====================================================
    // MARK NOTIFICATION AS READ
    // =====================================================

    public Notification markAsRead(Long notificationId) 
    {

        Notification notification =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() -> new RuntimeException("Notification not found."));

        notification.setReadStatus(true);

        return notificationRepository.save(notification);
    }

    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    public void deleteNotification(Long notificationId) 
    {

        if (!notificationRepository.existsById(notificationId)) 
        {

            throw new RuntimeException("Notification not found.");
        }

        notificationRepository.deleteById(notificationId);
    }
}