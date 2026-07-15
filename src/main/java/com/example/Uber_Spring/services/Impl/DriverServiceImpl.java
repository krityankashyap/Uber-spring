package com.example.Uber_Spring.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uber_Spring.DTOS.DriverRequestDTO;
import com.example.Uber_Spring.DTOS.DriverResponseDTO;
import com.example.Uber_Spring.entities.Drivers;
import com.example.Uber_Spring.mapper.DriverMapper;
import com.example.Uber_Spring.repository.DriverRepository;
import com.example.Uber_Spring.services.DriverService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService{

  private final DriverRepository driverRepository;
  private final DriverMapper driverMapper;

  @Override
    @Transactional(readOnly = true)
    public Optional<DriverResponseDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(driverMapper::toResponseDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DriverResponseDTO> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<DriverResponseDTO> getDriverByEmail(String email) {
        return driverRepository.findByEmail(email)
                .map(driverMapper::toResponseDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DriverResponseDTO> getAvailableDrivers() {
        return driverRepository.findAll().stream()
                .filter(Drivers::getIsAvailable)
                .map(driverMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public DriverResponseDTO create(DriverRequestDTO request) {
        if (driverRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Driver with email " + request.getEmail() + " already exists");
        }
        if (driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new IllegalArgumentException("Driver with license number " + request.getLicenseNumber() + " already exists");
        }
        
        Drivers driver = driverMapper.toEntity(request);
        Drivers savedDriver = driverRepository.save(driver);
        return driverMapper.toResponseDTO(savedDriver);
    }
    
    @Override
    public DriverResponseDTO update(Long id, DriverRequestDTO request) {
        Drivers driver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + id));
        
        // Check if email is being changed and if new email already exists
        if (!driver.getEmail().equals(request.getEmail()) && 
            driverRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Driver with email " + request.getEmail() + " already exists");
        }
        
        // Check if license number is being changed and if new license already exists
        if (!driver.getLicenseNumber().equals(request.getLicenseNumber()) && 
            driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new IllegalArgumentException("Driver with license number " + request.getLicenseNumber() + " already exists");
        }
        
        driverMapper.updateEntity(driver, request);
        Drivers updatedDriver = driverRepository.save(driver);
        return driverMapper.toResponseDTO(updatedDriver);
    }
    
    @Override
    public void delete(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new IllegalArgumentException("Driver not found with id: " + id);
        }
        driverRepository.deleteById(id);
    }

}
