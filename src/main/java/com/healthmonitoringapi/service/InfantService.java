package com.healthmonitoringapi.service;

import java.util.List;

import com.healthmonitoringapi.entity.Infant;

public interface InfantService {

	Infant findById(Integer id) throws Exception;

	Infant save(Infant infant) throws Exception;

	List<Infant> findAll();
	
}