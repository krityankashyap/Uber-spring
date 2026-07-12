package com.example.Uber_Spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Uber_Spring.entities.Passenger;



@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

  Optional<Passenger> findByEmail(String email);

  boolean existsByEmail(String email);
}
