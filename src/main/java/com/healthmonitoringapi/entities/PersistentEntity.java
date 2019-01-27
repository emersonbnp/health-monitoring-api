package com.healthmonitoringapi.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PersistentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
