package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.service.HotelService;


@RestController
@RequestMapping("/api")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	// get all the hotel
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN') || hasAuthority('NORMAL')")
	@GetMapping("/hotel")
	public ResponseEntity<List<Hotel>> getAllhotel() {
		List<Hotel> allhotels = hotelService.getAllHotels();
		return ResponseEntity.ok(allhotels);
	}

	// Get the single hotel
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/hotel/{id}")
	public ResponseEntity<Hotel> gethotel(@PathVariable("id") String id) {
		Hotel hotel = hotelService.getHotel(id);
		return ResponseEntity.ok(hotel);
	}

	// add the hotel
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/addHotel")
	public ResponseEntity<Hotel> savehotel(@RequestBody Hotel hotel) {
		Hotel hotel1 = hotelService.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}

//	// update the hotel
//	@PutMapping("/hotel/{id}")
//	public ResponseEntity<ApiResponse> updateEmploee(@PathVariable("id") Long id, @RequestBody Employee employeeDts) {
//		return employeeService.updateEmploee(id, employeeDts);
//	}
//
//	// delete the hotel
//	@DeleteMapping("/hotel/{id}")
//	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable("id") Long id) {
//		return employeeService.deleteEmployee(id);
//	}

}

