package com.example.Uber_Spring.services.Impl;

import com.example.Uber_Spring.DTOS.DriverRequestDTO;
import com.example.Uber_Spring.DTOS.DriverResponseDTO;

public interface DriverWriteService {

 DriverResponseDTO create(DriverRequestDTO driverRequestDTO);
 DriverResponseDTO update(Long id, DriverRequestDTO driverRequestDTO);

 void delete(Long id);
}
