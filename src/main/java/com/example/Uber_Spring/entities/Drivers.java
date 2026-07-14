package com.example.Uber_Spring.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "drivers")
public class Drivers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false, unique = true)
  private String email;
  
  @Column(nullable = false, unique = true)
  private String phoneNumber;

  private String vehicleModel;
  
  @Column(nullable = false, unique = true)
  private String licenseNumber;
  
  private String vehiclePlateNumber;

  private Boolean isAvailable;
  
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist  // This annotation is used to specify a method that should be called before the entity is persisted (saved) to the database.
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate // This annotation is used to specify a method that should be called before the entity is updated in the database.
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

}
