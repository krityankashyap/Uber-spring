package com.example.Uber_Spring.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Uber_Spring.DTOS.BookingResponseDTO;

@Service
public interface BookingReadService {
  
  Optional<BookingResponseDTO> findById(Long bookingId);
  List<BookingResponseDTO> findAll();

  List<BookingResponseDTO> findByDriverId(Long driverId);
  List<BookingResponseDTO> findByPassengerId(Long passengerId);

}
