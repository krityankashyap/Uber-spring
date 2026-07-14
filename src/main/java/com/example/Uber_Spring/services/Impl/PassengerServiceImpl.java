package com.example.Uber_Spring.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uber_Spring.DTOS.PassenegerResponseDTO;
import com.example.Uber_Spring.DTOS.PassengerRequestDTO;
import com.example.Uber_Spring.entities.Passenger;
import com.example.Uber_Spring.mapper.PassengerMapper;
import com.example.Uber_Spring.repository.PassengerRepository;
import com.example.Uber_Spring.services.PassenegerService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassenegerService {

  private final PassengerRepository passengerRepository;
  private final PassengerMapper passengerMapper;

  @Override
    @Transactional(readOnly = true)
    public Optional<PassenegerResponseDTO> getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(passengerMapper::toResponseDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PassenegerResponseDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(passengerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PassenegerResponseDTO> getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email)
                .map(passengerMapper::toResponseDTO);
    }
    
    @Override
    public PassenegerResponseDTO create(PassengerRequestDTO request) {
        if (passengerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Passenger with email " + request.getEmail() + " already exists");
        }
        
        Passenger passenger = passengerMapper.toEntity(request);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toResponseDTO(savedPassenger);
    }
    
    @Override
    public PassenegerResponseDTO update(Long id, PassengerRequestDTO request) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found with id: " + id));
        
        // Check if email is being changed and if new email already exists
        if (!passenger.getEmail().equals(request.getEmail()) && 
            passengerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Passenger with email " + request.getEmail() + " already exists");
        }
        
        passengerMapper.updateEntity(passenger, request);
        Passenger updatedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toResponseDTO(updatedPassenger);
    }
    
    @Override
    public void delete(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new IllegalArgumentException("Passenger not found with id: " + id);
        }
        passengerRepository.deleteById(id);
    }

}
