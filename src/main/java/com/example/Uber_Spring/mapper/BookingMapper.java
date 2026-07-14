package com.example.Uber_Spring.mapper;

import org.springframework.stereotype.Component;

import com.example.Uber_Spring.DTOS.BookingRequestDTO;
import com.example.Uber_Spring.DTOS.BookingResponseDTO;
import com.example.Uber_Spring.entities.BookingStatus;
import com.example.Uber_Spring.entities.Bookings;
import com.example.Uber_Spring.entities.Drivers;
import com.example.Uber_Spring.entities.Passenger;

@Component
public class BookingMapper {

  public Bookings toEntity(BookingRequestDTO bookingRequestDTO, Passenger passenger, Drivers driver){
   
    BookingStatus status= driver!= null ? BookingStatus.CONFIRMED : BookingStatus.PENDING;

    return Bookings.builder()
            .passenger(passenger)
            .driver(driver)
            .pickupLocation(bookingRequestDTO.getPickupLocation())
            .dropoffLocation(bookingRequestDTO.getDropoffLocation())
            .fare(bookingRequestDTO.getFare())
            .status(status)
            .scheduledPickupTime(bookingRequestDTO.getScheduledPickupTime())
            .build();
  }

  public BookingResponseDTO toResponseDTO(Bookings booking) {
    return BookingResponseDTO.builder()
                .id(booking.getId())
                .passengerId(booking.getPassenger() != null ? booking.getPassenger().getId() : null)
                .passengerName(booking.getPassenger() != null ? booking.getPassenger().getName() : null)
                .driverId(booking.getDriver() != null ? booking.getDriver().getId() : null)
                .driverName(booking.getDriver() != null ? booking.getDriver().getName() : null)
                .pickupLocation(booking.getPickupLocation())
                .dropoffLocation(booking.getDropoffLocation())
                .status(booking.getStatus())
                .fare(booking.getFare())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledPickupTime(booking.getScheduledPickupTime())
                .actualPickupTime(booking.getActualPickupTime())
                .completedAt(booking.getCompletedAt())
                .build();
  }
  

  public void updateEntity(Bookings booking, BookingRequestDTO request, Passenger passenger, Drivers driver) {
    booking.setPassenger(passenger);
    booking.setDriver(driver);
    booking.setPickupLocation(request.getPickupLocation());
    booking.setDropoffLocation(request.getDropoffLocation());
    booking.setFare(request.getFare());
    booking.setScheduledPickupTime(request.getScheduledPickupTime());
    
    // Update status based on driver assignment
    if (driver != null && booking.getStatus() == BookingStatus.PENDING) {
        booking.setStatus(BookingStatus.CONFIRMED);
    }
}
}
