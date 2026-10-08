package com.autophile.yatra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autophile.yatra.entity.DriverAssignment;

public interface DriverAssignmentRepository extends JpaRepository<DriverAssignment, Long> 
{

    Optional<DriverAssignment> findByBookingId(Long bookingId);

    List<DriverAssignment> findByDriverId(Long driverId);

    Optional<DriverAssignment>
    findTopByDriverIdOrderByAssignmentIdDesc(Long driverId);

    boolean existsByBookingId(Long bookingId);
}