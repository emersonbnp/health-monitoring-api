package com.healthmonitoringapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.healthmonitoringapi.entity.Address;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddressDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271055302648034381L;
	private Integer id;
	@NotNull(message = "Street name should not be empty")
	private String street;
	@NotNull(message = "City name should not be empty")
	private String city;
	@NotNull(message = "District name should not be empty")
	private String district;
	@NotNull(message = "State name should not be empty")
	private String state;
	@NotNull(message = "Street number should not be empty")
	private String number;
	@NotNull(message = "Zipcode should not be empty")
	private String zipcode;
	@JsonInclude(Include.NON_NULL)
	private String description;

	void parse(Address entity) {
		this.id = entity.getId();
		this.street = entity.getStreet();
		this.city = entity.getCity();
		this.district = entity.getDistrict();
		this.state = entity.getState();
		this.number = entity.getNumber();
		this.zipcode = entity.getZipcode();
		this.description = entity.getDescription();
	}

}
