package com.example.Uber_Spring.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

import com.example.Uber_Spring.DTOS.DriverLocationDto;
import com.example.Uber_Spring.services.LocationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisLocationServiceImpl implements LocationService{

  private final String Driver_LOCATION_KEY_PREFIX = "driver_location:"; // Prefix for Redis keys to store driver locations

  private final StringRedisTemplate stringRedisTemplate;

	@Override
	public Boolean saveDriverLocation(Long driverId, Double latitude, Double longitude) {
    
    GeoOperations<String, String> geoOperations= stringRedisTemplate.opsForGeo();

    geoOperations.add(Driver_LOCATION_KEY_PREFIX, 
                new RedisGeoCommands.GeoLocation<>(driverId.toString(), 
                 new Point(latitude, longitude)));

    return true;
	}

	@Override
	public List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude, Double radius) {
		GeoOperations<String, String> geoOperations= stringRedisTemplate.opsForGeo();

    Distance circleRadius= new Distance(radius, Metrics.KILOMETERS);

    Circle circle= new Circle(new Point(latitude, longitude), circleRadius);

    GeoResults<GeoLocation<String>> geoResults= geoOperations.radius(Driver_LOCATION_KEY_PREFIX, circle);

    List<DriverLocationDto> nearbyDriver= new ArrayList<>();

    for(GeoResult<GeoLocation<String>> result: geoResults){

      Point point = geoOperations.position(Driver_LOCATION_KEY_PREFIX, result.getContent().getName()).get(0);

      DriverLocationDto driverLocationDto= DriverLocationDto.builder()
                                       .driverId(Long.parseLong(result.getContent().getName()))
                                       .latitude(point.getY())
                                       .longitude(point.getX())
                                       .build();

      nearbyDriver.add(driverLocationDto);
    }

    return nearbyDriver;
	}
}
