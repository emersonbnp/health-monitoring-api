package com.healthmonitoringapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entities.Parent;
import com.healthmonitoringapi.repositories.ParentRepository;
import com.healthmonitoringapi.services.ParentService;

@Service
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepository parentRepository;
	
	public void save(Parent parent) {

	}

	public void update(Parent parent) {
		
	}

	@Override
	public Parent findById(Integer id) {
		return parentRepository.findById(id).get();
	}
}