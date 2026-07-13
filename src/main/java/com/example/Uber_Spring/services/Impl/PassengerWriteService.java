package com.example.Uber_Spring.services.Impl;

import com.example.Uber_Spring.DTOS.PassenegerResponseDTO;
import com.example.Uber_Spring.DTOS.PassengerRequestDTO;

public interface PassengerWriteService {
 
  PassenegerResponseDTO create(PassengerRequestDTO passengerRequestDTO);

  PassenegerResponseDTO update(Long id, PassengerRequestDTO passengerRequestDTO);

  void delete(Long id);
}
