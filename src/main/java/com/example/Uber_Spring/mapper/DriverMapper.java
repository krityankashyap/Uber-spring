package com.example.Uber_Spring.mapper;

import org.springframework.stereotype.Component;

import com.example.Uber_Spring.DTOS.DriverRequestDTO;
import com.example.Uber_Spring.DTOS.DriverResponseDTO;
import com.example.Uber_Spring.entities.Drivers;


@Component
public class DriverMapper {

  public Drivers toEntity(DriverRequestDTO driverRequestDTO){
    return Drivers.builder()
            .name(driverRequestDTO.getName())
            .email(driverRequestDTO.getEmail())
            .phoneNumber(driverRequestDTO.getPhoneNumber())
            .licenseNumber(driverRequestDTO.getLicenseNumber())
            .vehiclePlateNumber(driverRequestDTO.getVehiclePlateNumber())
            .vehicleModel(driverRequestDTO.getVehicleModel())
            .isAvailable(driverRequestDTO.getIsAvailable())
            .build();
  }

  public DriverResponseDTO toResponseDTO(Drivers driver){
    return DriverResponseDTO.builder()
            .id(driver.getId())
            .name(driver.getName())
            .email(driver.getEmail())
            .phoneNumber(driver.getPhoneNumber())
            .licenseNumber(driver.getLicenseNumber())
            .vehiclePlateNumber(driver.getVehiclePlateNumber())
            .vehicleModel(driver.getVehicleModel())
            .isAvailable(driver.getIsAvailable())
            .createdAt(driver.getCreatedAt())
            .updatedAt(driver.getUpdatedAt())
            .build();
  }

  public void updateEntity(Drivers driver, DriverRequestDTO driverRequestDTO){
    driver.setName(driverRequestDTO.getName());
    driver.setEmail(driverRequestDTO.getEmail());
    driver.setPhoneNumber(driverRequestDTO.getPhoneNumber());
    driver.setLicenseNumber(driverRequestDTO.getLicenseNumber());
    driver.setVehiclePlateNumber(driverRequestDTO.getVehiclePlateNumber());
    driver.setVehicleModel(driverRequestDTO.getVehicleModel());
    driver.setIsAvailable(driverRequestDTO.getIsAvailable());
  }

  

}
