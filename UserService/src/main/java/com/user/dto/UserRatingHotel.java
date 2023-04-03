package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingHotel {
	private String userId;
	private String userName;
	private String hotelId;
	private String hotelName;
	private int rating;
}
