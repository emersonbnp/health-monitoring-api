package com.healthmonitoringapi.service;

import com.healthmonitoringapi.entity.Parent;

public interface ParentService {
	
	Parent save(Parent parent);
	Parent update(Parent parent);
	Parent findById(Integer id);
}