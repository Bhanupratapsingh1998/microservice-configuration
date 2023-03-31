package com.user.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "NAME", nullable = false,length = 30)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "ABOUT", nullable = false)	
	private String about;
	
	@Transient
	private List<Rating>rartings = new ArrayList<Rating>();

}
