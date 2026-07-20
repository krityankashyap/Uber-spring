package com.example.Uber_Spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Uber_Spring.DTOS.PassenegerResponseDTO;
import com.example.Uber_Spring.DTOS.PassengerRequestDTO;
import com.example.Uber_Spring.services.PassenegerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassenegerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassenegerResponseDTO>> getAllPassengers() {
        List<PassenegerResponseDTO> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassenegerResponseDTO> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PassenegerResponseDTO> getPassengerByEmail(@PathVariable String email) {
        return passengerService.getPassengerByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PassenegerResponseDTO> createPassenger(@Valid @RequestBody PassengerRequestDTO request) {
        try {
            PassenegerResponseDTO passenger = passengerService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassenegerResponseDTO> updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody PassengerRequestDTO request) {
        try {
            PassenegerResponseDTO passenger = passengerService.update(id, request);
            return ResponseEntity.ok(passenger);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        try {
            passengerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
