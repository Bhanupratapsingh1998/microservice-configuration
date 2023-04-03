package com.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserRatingHotel;
import com.user.entities.User;
import com.user.service.UserService;
import com.user.service.UserServiceImpl;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	// get all the user
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUsers = userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}

	// get all user rating hotels
	@GetMapping("/user-rating-hotel/{userId}")
	public List<UserRatingHotel> getUserRatingHotel(@PathVariable String userId) {
		return userService.getUserRatingHotel(userId);
	}

	// Get the single user
	@GetMapping("/user/{id}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}

	int retryCount = 1;

	// Creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
		logger.info("retry count: {}", retryCount);
		retryCount++;
//		logger.info("Fallback is executed because service is down", ex.getMessage());
		User user = User.builder().email("example@gmail.com").name("Test")
				.about("This dummy user is created because some service is down").userId("121").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// add the employee
	@PostMapping("/addUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

//	// update the employee
//	@PutMapping("/Employees/{id}")
//	public ResponseEntity<ApiResponse> updateEmploee(@PathVariable("id") Long id, @RequestBody Employee employeeDts) {
//		return employeeService.updateEmploee(id, employeeDts);
//	}
//
//	// delete the employee
//	@DeleteMapping("/Employees/{id}")
//	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable("id") Long id) {
//		return employeeService.deleteEmployee(id);
//	}

}
