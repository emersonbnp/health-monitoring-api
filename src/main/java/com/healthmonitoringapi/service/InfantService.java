package com.healthmonitoringapi.service;

import java.util.List;

import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.exception.EntityNotFoundException;

public interface InfantService {

	Infant findById(Integer id) throws EntityNotFoundException;

	Infant save(Infant infant) throws EntityNotFoundException;

	List<Infant> findAll();

	List<Infant> findByParent(Parent parent);

	Infant findByIdAndParent(Integer id, Parent parent) throws EntityNotFoundException;
	
}