package com.example.Uber_Spring.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "bookings")
public class Bookings {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name= "passenger_id" , nullable = false)
  private Passenger passenger;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name= "driver_id" , nullable = false)
  private Drivers driver;
  
  @Column(nullable = false)
  private String pickupLocation;
  
  @Column(nullable = false)
  private String dropoffLocation;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  private BookingStatus status = BookingStatus.PENDING;
  
  @Column(nullable = false)
  private BigDecimal fare;

  @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime scheduledPickupTime;
    
    private LocalDateTime actualPickupTime;
    
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
