package com.user.service;

import java.util.List;

import com.user.dto.UserRatingHotel;
import com.user.entities.User;

public interface UserService {

	// create user
	User saveUser(User user);

	// get all user
	List<User> getAllUser();

	//Get all user rating hotels
	List<UserRatingHotel> getUserRatingHotel(String userId);

	// get single user
	User getUser(String userId);

}
