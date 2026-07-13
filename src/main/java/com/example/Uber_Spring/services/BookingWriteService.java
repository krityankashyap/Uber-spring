package com.example.Uber_Spring.services;

import com.example.Uber_Spring.DTOS.BookingRequestDTO;
import com.example.Uber_Spring.DTOS.BookingResponseDTO;
import com.example.Uber_Spring.entities.BookingStatus;

public interface BookingWriteService {

  BookingResponseDTO create(BookingRequestDTO bookingRequestDTO);
  BookingResponseDTO update(Long id, BookingRequestDTO bookingRequestDTO);
  BookingResponseDTO updateStatus(Long id, BookingStatus status);

  void deleteById(Long id);
}
