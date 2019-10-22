package com.healthmonitoringapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.repository.InfantRepository;
import com.healthmonitoringapi.service.InfantService;

@Service
public class InfantServiceImpl implements InfantService {

	@Autowired
	private InfantRepository infantRepository;

	@Override
	public Infant findById(Integer id) throws Exception {
		return this.infantRepository.findById(id).get();
	}

	@Override
	public Infant save(Infant infant) throws Exception {
		return this.infantRepository.save(infant);
	}

	@Override
	public List<Infant> findAll() {
		return this.infantRepository.findAll();
	}

}