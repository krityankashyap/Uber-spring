package com.example.Uber_Spring.services.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Uber_Spring.DTOS.PassenegerResponseDTO;

public interface PassengerReadService {

  Optional<PassenegerResponseDTO> getPassengerById(Long id);

  List<PassenegerResponseDTO> getAllPassengers();

  Optional<PassenegerResponseDTO> getPassengerByEmail(String email);
}
