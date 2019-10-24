package com.healthmonitoringapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.exception.EntityNotFoundException;
import com.healthmonitoringapi.repository.UserRepository;
import com.healthmonitoringapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) throws EntityNotFoundException {
		if (userRepository.existsById(user.getId())) {
			return userRepository.save(user);
		} else {
			throw new EntityNotFoundException("Entity not found.");
		}
	}

	@Override
	public User findById(Integer id) throws EntityNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public User findByEmailAndPassword(String username, String password) throws EntityNotFoundException {
		return userRepository.findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
