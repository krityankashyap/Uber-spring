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
public class PassenegerResponseDTO {
 
  private Long id;

  private String name;

  private String email;

  private String phoneNumber;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
