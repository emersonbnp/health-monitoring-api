package com.healthmonitoringapi.services;

import java.util.List;
import java.util.Map;

import com.healthmonitoringapi.entities.Parent;

public interface ParentService {
	
	public void save(Parent parent);
	public void update(Parent parent);
	public List<Parent> find(Map<String, String> filter);
}