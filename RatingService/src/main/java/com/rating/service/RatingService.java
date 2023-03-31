package com.rating.service;

import java.util.List;
import com.rating.entities.Rating;

public interface RatingService {
	// create Rating
	Rating saveRating(Rating Rating);

	// get all ratings
	List<Rating> getRatings();

	// get rating by userId
	List<Rating> getAllRatingByUserId(String userId);

	// get rating by hoteId
	List<Rating> getAllRatingByHotelId(String hotelId);
}
