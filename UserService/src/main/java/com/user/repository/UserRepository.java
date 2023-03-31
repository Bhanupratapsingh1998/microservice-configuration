package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
