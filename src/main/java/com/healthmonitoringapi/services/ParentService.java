package com.healthmonitoringapi.services;

import com.healthmonitoringapi.entities.Parent;

public interface ParentService {
	
	void save(Parent parent);
	void update(Parent parent);
	Parent findById(Integer id);
}