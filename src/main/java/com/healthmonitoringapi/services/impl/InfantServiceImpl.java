package com.healthmonitoringapi.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.repositories.InfantRepository;
import com.healthmonitoringapi.services.InfantService;

@Service
public class InfantServiceImpl implements InfantService {
	
	@Autowired
	private InfantRepository infantRepository;
	
	@Transactional
	@Override
	public Infant findById(Integer id) throws Exception {
		return this.infantRepository.findById(id).get();
	}
	
	@Transactional
	@Override
	public Infant save(Infant infant) throws Exception {
		return this.infantRepository.save(infant);
	}

	@Transactional
	@Override
	public List<Infant> findAll() {
		return this.infantRepository.findAll();
	}
	
}