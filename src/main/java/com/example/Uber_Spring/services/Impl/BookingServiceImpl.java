package com.example.Uber_Spring.services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uber_Spring.DTOS.BookingRequestDTO;
import com.example.Uber_Spring.DTOS.BookingResponseDTO;
import com.example.Uber_Spring.entities.BookingStatus;
import com.example.Uber_Spring.entities.Bookings;
import com.example.Uber_Spring.entities.Drivers;
import com.example.Uber_Spring.entities.Passenger;
import com.example.Uber_Spring.mapper.BookingMapper;
import com.example.Uber_Spring.mapper.DriverMapper;
import com.example.Uber_Spring.mapper.PassengerMapper;
import com.example.Uber_Spring.repository.BookingRepository;
import com.example.Uber_Spring.repository.DriverRepository;
import com.example.Uber_Spring.repository.PassengerRepository;
import com.example.Uber_Spring.services.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;
  private final BookingMapper bookingMapper;
  private final PassengerRepository passengerRepository;
  private final DriverRepository driverRepository;

  @Override
    @Transactional(readOnly = true)
    public Optional<BookingResponseDTO> findById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toResponseDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> findAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> findByPassengerId(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found with id: " + passengerId));
        return bookingRepository.findByPassenger(passenger).stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> findByDriverId(Long driverId) {
        Drivers driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + driverId));
        return bookingRepository.findByDriver(driver).stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public BookingResponseDTO create(BookingRequestDTO request) {
        Passenger passenger = passengerRepository.findById(request.getPassengerId())
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found with id: " + request.getPassengerId()));
        
        Drivers driver = null;
        if (request.getDriverId() != null) {
            driver = driverRepository.findById(request.getDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + request.getDriverId()));
            
            if (!driver.getIsAvailable()) {
                throw new IllegalArgumentException("Driver with id " + request.getDriverId() + " is not available");
            }
        }
        
        Bookings booking = bookingMapper.toEntity(request, passenger, driver);
        
        // If driver is assigned, mark as unavailable
        if (driver != null) {
            driver.setIsAvailable(false);
            driverRepository.save(driver);
        }
        
        Bookings savedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(savedBooking);
    }
    
    @Override
    public BookingResponseDTO update(Long id, BookingRequestDTO request) {
        Bookings booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        
        Passenger passenger = passengerRepository.findById(request.getPassengerId())
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found with id: " + request.getPassengerId()));
        
        Drivers driver = null;
        if (request.getDriverId() != null) {
            driver = driverRepository.findById(request.getDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + request.getDriverId()));
        }
        
        // Handle driver availability when updating
        Drivers previousDriver = booking.getDriver();
        if (previousDriver != null && !previousDriver.equals(driver)) {
            previousDriver.setIsAvailable(true);
            driverRepository.save(previousDriver);
        }
        
        if (driver != null && !driver.equals(previousDriver)) {
            if (!driver.getIsAvailable()) {
                throw new IllegalArgumentException("Driver with id " + request.getDriverId() + " is not available");
            }
            driver.setIsAvailable(false);
            driverRepository.save(driver);
        }
        
        bookingMapper.updateEntity(booking, request, passenger, driver);
        Bookings updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(updatedBooking);
    }
    
    @Override
    public BookingResponseDTO updateStatus(Long id, BookingStatus status) {
        Bookings booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        
        booking.setStatus(status);
        
        // Handle status-specific logic
        if (status == BookingStatus.IN_PROGRESS && booking.getActualPickupTime() == null) {
            booking.setActualPickupTime(LocalDateTime.now());
        } else if (status ==BookingStatus.COMPLETED) {
            booking.setCompletedAt(LocalDateTime.now());
            // Release driver
            if (booking.getDriver() != null) {
                Drivers driver = booking.getDriver();
                driver.setIsAvailable(true);
                driverRepository.save(driver);
            }
        } else if (status == BookingStatus.CANCELLED) {
            // Release driver
            if (booking.getDriver() != null) {
                Drivers driver = booking.getDriver();
                driver.setIsAvailable(true);
                driverRepository.save(driver);
            }
        }
        
        Bookings updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(updatedBooking);
    }
    
    @Override
    public void deleteById(Long id) {
        Bookings booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        
        // Release driver if assigned
        if (booking.getDriver() != null) {
            Drivers driver = booking.getDriver();
            driver.setIsAvailable(true);
            driverRepository.save(driver);
        }
        
        bookingRepository.deleteById(id);
    }

}
