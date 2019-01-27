package com.healthmonitoringapi.services;

import java.util.List;

import com.healthmonitoringapi.entities.Infant;

public interface InfantService {

	Infant findById(Integer id) throws Exception;

	Infant save(Infant infant) throws Exception;

	List<Infant> findAll();
	
}