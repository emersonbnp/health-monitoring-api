package com.healthmonitoringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthmonitoringapi.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer>{

}
