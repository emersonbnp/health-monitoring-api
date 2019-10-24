package com.healthmonitoringapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.entity.Parent;

@Repository
public interface InfantRepository extends JpaRepository<Infant, Long> {

	public Optional<Infant> findById(Integer id);

	public Optional<Infant> findByDevice(String device);

	public List<Infant> findByParent(Parent parent, Pageable pageable);

	public Optional<Infant> findByIdAndParent(Integer id, Parent parent);

}
