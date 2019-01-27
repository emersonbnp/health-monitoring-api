package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.healthmonitoringapi.entities.Infant;

public class InfantDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate birthday;
	private BigDecimal weight;

	public InfantDTO(String firstName, String lastName, LocalDate birthday, BigDecimal weight) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.weight = weight;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	public Infant shallowMapToEntity () {
		return new Infant(firstName, lastName, birthday, null);
	}
}
