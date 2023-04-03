package com.user.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query(value = "SELECT u.user_id as userId,u.name as userName,h.name as hotelName,r.rating as rating FROM micro_users u "
			+ "INNER JOIN user_ratings r ON u.user_id = r.user_id " + "INNER JOIN hotels h ON h.id = r.hotel_id "
			+ "WHERE r.user_id = :userId", nativeQuery = true)
	List<Map<String,Object>> findByUserId(@Param("userId") String userId);
}
