package com.example.Uber_Spring.DTOS;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDTO {

  private Long driverId;
  
  @NotNull(message = "Passenger ID is required")
  private Long passengerId;

  @NotNull(message = "Pickup location is required")
  private String pickupLocation;

  @NotNull(message = "Dropoff location is required")
  private String dropoffLocation;

  @NotNull(message = "Fare is required")
  @Positive(message = "Fare must be positive")
  private BigDecimal fare;

  private LocalDateTime scheduledPickupTime;
}
