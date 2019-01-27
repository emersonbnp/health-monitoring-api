package com.healthmonitoringapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthmonitoringapi.entities.Infant;

@Repository
public interface InfantRepository extends JpaRepository<Infant, Long> {

	public Optional<Infant> findById(Integer id);
	
}
