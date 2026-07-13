package com.example.Uber_Spring.services;

import java.util.List;
import java.util.Optional;

import com.example.Uber_Spring.DTOS.DriverResponseDTO;

public interface DriverReadService {
 
  Optional<DriverResponseDTO> getDriverById(Long id);
  List<DriverResponseDTO> getAllDrivers();
  Optional<DriverResponseDTO> getDriverByEmail(String email);

  List<DriverResponseDTO> getAvailableDrivers();
}