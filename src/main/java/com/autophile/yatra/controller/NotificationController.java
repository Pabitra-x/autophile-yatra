package com.autophile.yatra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autophile.yatra.entity.Notification;
import com.autophile.yatra.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController 
{

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) 
    {

        this.notificationService = notificationService;
    }

    // =====================================================
    // CREATE CUSTOMER NOTIFICATION
    // =====================================================

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(

            @RequestParam Long customerId,

            @RequestParam(required = false) Long bookingId,

            @RequestParam String title,

            @RequestParam String message,

            @RequestParam String notificationType) {

        Notification notification = notificationService.createNotification(customerId,bookingId,title,message,notificationType);

        return ResponseEntity.ok(notification);
    }

    // =====================================================
    // CREATE ADMIN NOTIFICATION
    // =====================================================

    @PostMapping("/admin/create")
    public ResponseEntity<Notification> createAdminNotification(

            @RequestParam Long adminId,

            @RequestParam(required = false) Long bookingId,

            @RequestParam String title,

            @RequestParam String message,

            @RequestParam String notificationType) {

        Notification notification = notificationService.createAdminNotification(adminId,bookingId,title,message,notificationType);

        return ResponseEntity.ok(notification);
    }

    // =====================================================
    // GET CUSTOMER NOTIFICATIONS
    // =====================================================

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Notification>>
    getCustomerNotifications(@PathVariable Long customerId) 
    {

        return ResponseEntity.ok(notificationService .getCustomerNotifications(customerId));
    }

    // =====================================================
    // GET CUSTOMER UNREAD NOTIFICATIONS
    // =====================================================

    @GetMapping("/customer/{customerId}/unread")
    public ResponseEntity<List<Notification>>
    getUnreadCustomerNotifications(@PathVariable Long customerId) 
    {

        return ResponseEntity.ok(notificationService.getUnreadCustomerNotifications(customerId));
    }

    // =====================================================
    // GET CUSTOMER UNREAD COUNT
    // =====================================================

    @GetMapping("/customer/{customerId}/unread-count")
    public ResponseEntity<Long>
    getCustomerUnreadCount(@PathVariable Long customerId) {

        return ResponseEntity.ok(notificationService.getCustomerUnreadCount(customerId));
    }

    // =====================================================
    // GET ADMIN NOTIFICATIONS
    // =====================================================

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Notification>>
    getAdminNotifications(
            @PathVariable Long adminId) 
    {

        return ResponseEntity.ok(notificationService.getAdminNotifications(adminId));
    }

    // =====================================================
    // GET ADMIN UNREAD NOTIFICATIONS
    // =====================================================

    @GetMapping("/admin/{adminId}/unread")
    public ResponseEntity<List<Notification>>
    getUnreadAdminNotifications(@PathVariable Long adminId) 
    {

        return ResponseEntity.ok(notificationService.getUnreadAdminNotifications(adminId));
    }

    // =====================================================
    // GET ADMIN UNREAD COUNT
    // =====================================================

    @GetMapping("/admin/{adminId}/unread-count")
    public ResponseEntity<Long>
    getAdminUnreadCount(@PathVariable Long adminId) 
    {

        return ResponseEntity.ok(notificationService.getAdminUnreadCount(adminId));
    }

    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    @GetMapping("/all")
    public ResponseEntity<List<Notification>>
    getAllNotifications() 
    {

        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    // =====================================================
    // MARK NOTIFICATION AS READ
    // =====================================================

    @PutMapping("/read/{notificationId}")
    public ResponseEntity<Notification>
    markAsRead(@PathVariable Long notificationId) 
    {

        return ResponseEntity.ok(notificationService.markAsRead(notificationId));
    }

    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String>
    deleteNotification(@PathVariable Long notificationId) 
    {

        notificationService.deleteNotification(notificationId);

        return ResponseEntity.ok("Notification deleted successfully.");
    }
}