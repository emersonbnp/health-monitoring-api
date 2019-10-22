package com.healthmonitoringapi.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.repository.ParentRepository;
import com.healthmonitoringapi.service.ParentService;
import com.healthmonitoringapi.service.UserService;

@Service
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private UserService userService;

	public Parent save(Parent parent) {
		User user = parent.getUser();
		userService.save(user);
		
		return parentRepository.save(parent);
	}

	public Parent update(Parent parent) {
		return parentRepository.save(parent);
	}

	@Override
	public Parent findById(Integer id) throws EntityNotFoundException {
		return parentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
}