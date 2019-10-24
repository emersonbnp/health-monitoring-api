package com.healthmonitoringapi.service;

import java.util.Optional;

import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.exception.EntityNotFoundException;

public interface UserService {

	User save(User user);

	User update(User user) throws EntityNotFoundException;

	User findById(Integer id) throws EntityNotFoundException;

	User findByEmailAndPassword(String email, String password) throws EntityNotFoundException;

	Optional<User> findByEmail(String email);
}