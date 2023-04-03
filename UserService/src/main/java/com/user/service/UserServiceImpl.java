package com.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.dto.UserRatingHotel;
import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exception.ResourceNotFoundException;
import com.user.external.HotelService;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUser = UUID.randomUUID().toString();
		user.setUserId(randomUser);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public List<UserRatingHotel> getUserRatingHotel(String userId) {
		List<Map<String, Object>> hotellist = userRepository.findByUserId(userId);
		List<UserRatingHotel> userRatingHotelList = new ArrayList<UserRatingHotel>();
		ObjectMapper mapper = new ObjectMapper();
		for (Map<String, Object> hotelMap : hotellist) {
			UserRatingHotel hotel = mapper.convertValue(hotelMap, UserRatingHotel.class);
			userRatingHotelList.add(hotel);
		}
		return userRatingHotelList;
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));

//		 fetch ratings of the above user
		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/userRatings/" + user.getUserId(), Rating[].class);
//		Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/api/userRatings/" + user.getUserId(),
//				Rating[].class);
		logger.info("{}", Arrays.toString(ratingsOfUser));

		// map each rating to a hotel
		List<Rating> ratingList = Arrays.stream(ratingsOfUser).map(rating -> {
			// api call to the hotel service to get hotel
//			ResponseEntity<Hotel> hotelDetails = restTemplate
//					.getForEntity("http://localhost:8082/api/hotel/" + rating.getHotelId(), Hotel.class);

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			logger.info("status code : {}", hotelDetails.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		// set the list of ratings with hotel details in the user object
		user.setRartings(ratingList);

		return user;

	}

}
