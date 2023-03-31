package com.user.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.entities.Hotel;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/api/hotel/{hottelId}")
	Hotel getHotel(@PathVariable("hottelId") String hottelId);
	
	
}
