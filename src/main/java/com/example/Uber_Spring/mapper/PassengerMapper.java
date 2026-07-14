package com.example.Uber_Spring.mapper;

import org.springframework.stereotype.Component;

import com.example.Uber_Spring.DTOS.PassenegerResponseDTO;
import com.example.Uber_Spring.DTOS.PassengerRequestDTO;
import com.example.Uber_Spring.entities.Passenger;

@Component
public class PassengerMapper {

  public Passenger toEntity(PassengerRequestDTO passengerRequestDTO){
    return Passenger.builder()
             .name(passengerRequestDTO.getName())
             .email(passengerRequestDTO.getEmail())
             .phoneNumber(passengerRequestDTO.getPhoneNumber())
             .build();
  }

  public PassenegerResponseDTO toResponseDTO(Passenger passenger){
    return PassenegerResponseDTO.builder()
              .id(passenger.getId())
              .name(passenger.getName())
              .email(passenger.getEmail())
              .phoneNumber(passenger.getPhoneNumber())
              .createdAt(passenger.getCreatedAt())
              .updatedAt(passenger.getUpdatedAt())
              .build();
  }

  public void updateEntity(Passenger passenger, PassengerRequestDTO passengerRequestDTO){
    passenger.setName(passengerRequestDTO.getName());
    passenger.setEmail(passengerRequestDTO.getEmail());
    passenger.setPhoneNumber(passengerRequestDTO.getPhoneNumber());
  }

}
