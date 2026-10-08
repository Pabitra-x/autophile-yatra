# 🚗 Autophile Yatra

Autophile Yatra is a full-stack travel and trip booking management application built using Java and Spring Boot.

## 📌 Project Overview

Autophile Yatra allows customers to explore tour packages, book trips, make payments, and track their trips.

The system also provides separate modules for **Admin** and **Driver** management.

## ✨ Features

### 👤 Customer
- Customer registration and login
- OTP verification
- Browse tour packages
- Book trips
- View booking status
- Make advance payment
- Make final payment
- View booking history
- Notifications
- Submit reviews
- Trip tracking
- Password reset

### 👨‍💼 Admin
- Admin login
- Customer management
- Driver management
- Tour package management
- Booking management
- Approve/reject bookings
- Driver assignment
- Payment management
- Review management
- Notifications
- Trip tracking
- Reports

### 🚗 Driver
- Driver login
- View assigned trips
- Trip tracking
- Driver password reset

## 💳 Payment Flow

```text
Customer books trip
        ↓
Admin reviews booking
        ↓
Admin approves booking
        ↓
Customer pays 25% advance
        ↓
Trip starts
        ↓
Customer pays remaining 75%
        ↓
Trip completed
