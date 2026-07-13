package com.example.Uber_Spring.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequestDTO {

  @NotNull(message = "Name is required")
  private String name;
  
  @NotNull(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;
  
  @NotNull(message = "Phone number is required")
  private String phoneNumber;
}
