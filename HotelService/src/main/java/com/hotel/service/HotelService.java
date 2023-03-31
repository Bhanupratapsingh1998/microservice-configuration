package com.hotel.service;

import java.util.List;

import com.hotel.entities.Hotel;

public interface HotelService {
	
	// create hotels
	Hotel saveHotel(Hotel hotel);

	// get all hotels
	List<Hotel> getAllHotels();

	// get single hotels
	Hotel getHotel(String hotelId);
}
