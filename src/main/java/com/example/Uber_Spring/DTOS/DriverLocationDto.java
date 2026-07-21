package com.example.Uber_Spring.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverLocationDto {
 
  private Long driverId;
  private Double latitude;
  private Double longitude;
}
