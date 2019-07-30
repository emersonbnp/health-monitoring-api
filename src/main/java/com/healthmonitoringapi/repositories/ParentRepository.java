package com.healthmonitoringapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthmonitoringapi.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer>{

}
