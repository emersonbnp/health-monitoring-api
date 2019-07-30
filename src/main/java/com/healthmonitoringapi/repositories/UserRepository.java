package com.healthmonitoringapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthmonitoringapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsernameAndPassword(String username, String password);
	
}
