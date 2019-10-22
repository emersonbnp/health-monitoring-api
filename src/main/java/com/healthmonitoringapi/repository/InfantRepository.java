package com.healthmonitoringapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthmonitoringapi.entity.Infant;

@Repository
public interface InfantRepository extends JpaRepository<Infant, Long> {

	public Optional<Infant> findById(Integer id);
	
	public Optional<Infant> findByDevice(String device);
	
}
