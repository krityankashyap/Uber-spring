package com.example.Uber_Spring.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.Uber_Spring.entities.BookingStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDTO {

  private Long id;

  private Long driverId;
  private String driverName;

  private Long passengerId;
  private String passengerName;

  private String pickupLocation;
  private String dropoffLocation;

  private BookingStatus status;

  private BigDecimal fare;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private LocalDateTime scheduledPickupTime;
  private LocalDateTime actualPickupTime;
  private LocalDateTime completedAt;
}
