package com.example.Uber_Spring.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequestDTO {
  
  @NotBlank(message = "Name is required")
  private String name;
  
  @NotBlank(message = "Email is required")
  private String email;
  
  @NotBlank(message = "Phone number is required")
  private String phoneNumber;
  
  @NotBlank(message = "License number is required")
  private String licenseNumber;

  private String vehicleModel;

  private String vehiclePlateNumber;
    
    @NotNull
    @Builder.Default
    private Boolean isAvailable = true;


}

