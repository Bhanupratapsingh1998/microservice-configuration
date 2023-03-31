package com.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.entities.Rating;
import com.rating.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating saveRating(Rating Rating) {
		String randomUser = UUID.randomUUID().toString();
		Rating.setRatingId(randomUser);
		return ratingRepository.save(Rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getAllRatingByUserId(String userId) {
		return (List<Rating>) ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingByHotelId(String hotelId) {
		return (List<Rating>) ratingRepository.findByHotelId(hotelId);
	}

}
