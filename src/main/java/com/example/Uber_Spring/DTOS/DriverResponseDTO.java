package com.example.Uber_Spring.DTOS;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponseDTO {

  private Long id;

  private String name;

  private String email;

  private String phoneNumber;

  private String licenseNumber;

  private String vehicleModel;

  private String vehiclePlateNumber;

  private Boolean isAvailable;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
