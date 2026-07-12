package com.example.Uber_Spring.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Uber_Spring.entities.Drivers;



@Repository
public interface DriverRepository extends JpaRepository<Drivers, Long>{

  Optional<Drivers> findByEmail(String email);
  Optional<Drivers> findByLicenseNumber(String licenseNumber);

  boolean existsByEmail(String email);
  boolean existsByLicenseNumber(String licenseNumber);
}
