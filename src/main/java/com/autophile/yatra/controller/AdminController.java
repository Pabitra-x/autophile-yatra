package com.autophile.yatra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autophile.yatra.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController 
{

    private final AdminService adminService;

    public AdminController(AdminService adminService) 
    {
        this.adminService = adminService;
    }

    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username,@RequestParam String password) 
    {

        String result = adminService.loginAdmin(username,password);

        return ResponseEntity.ok(result);
    }

    // =========================================================
    // ADMIN DASHBOARD
    // =========================================================

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() 
    {

        Map<String, Object> data = new HashMap<>();

        data.put("totalCustomers",adminService.getTotalCustomers());

        data.put("totalBookings",adminService.getTotalBookings());

        data.put("pendingRequests",adminService.getPendingRequests());

        data.put("approvedBookings",adminService.getApprovedBookings());

        data.put("completedBookings",adminService.getCompletedBookings());

        data.put("totalRevenue",adminService.getTotalRevenue());

        return ResponseEntity.ok(data);
    }
}