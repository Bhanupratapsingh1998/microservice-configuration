package com.rating.controller;

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

import com.rating.entities.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService ratingService;

	// get all the ratings
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN') || hasAuthority('NORMAL')")
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> ratings = ratingService.getRatings();
		return ResponseEntity.ok(ratings);
	}

	// Get all ratings by user id
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/userRatings/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable("userId") String userId) {
		List<Rating> rating = ratingService.getAllRatingByUserId(userId);
		return ResponseEntity.ok(rating);
	}

	// Get all ratings by hotel id
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/hotelRatings/{hotelId}")
	public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable("hotelId") String hotelId) {
		List<Rating> rating = ratingService.getAllRatingByHotelId(hotelId);
		return ResponseEntity.ok(rating);
	}

	// add the Ratings
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/addRatings")
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
		Rating rating1 = ratingService.saveRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
	}
}
