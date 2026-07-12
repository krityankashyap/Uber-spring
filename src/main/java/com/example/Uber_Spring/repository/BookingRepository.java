package com.example.Uber_Spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Uber_Spring.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long>{
  
  List<Bookings> findByDriverId(Long driverId);
  List<Bookings> findByPassengerId(Long passengerId);

  Optional<Bookings> findByIdAndDriverId(Long bookingId, Long driverId);
  Optional<Bookings> findByIdAndPassengerId(Long bookingId, Long passengerId);
}
