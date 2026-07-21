package com.example.Uber_Spring.services;

import java.util.List;

import com.example.Uber_Spring.DTOS.DriverLocationDto;

public interface LocationService {

  Boolean saveDriverLocation(Long driverId, Double latitude, Double longitude);

  List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude, Double radius);

}
