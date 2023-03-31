package com.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.entities.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	@Override
	public Hotel saveHotel(Hotel hotel) {
		String randomUser = UUID.randomUUID().toString();
		hotel.setId(randomUser);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("User does not exits with id : " + hotelId));
	}

}
