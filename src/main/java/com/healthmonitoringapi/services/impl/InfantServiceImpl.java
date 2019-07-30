package com.healthmonitoringapi.services.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.repositories.InfantRepository;
import com.healthmonitoringapi.services.InfantService;
import com.healthmonitoringapi.socket.DataInputServerSocket;

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
	
	@PostConstruct
	public void oninit() {
		DataInputServerSocket server = new DataInputServerSocket(infantRepository);
		try {
			server.init();
			Thread serverThread = new Thread(server);
			serverThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}