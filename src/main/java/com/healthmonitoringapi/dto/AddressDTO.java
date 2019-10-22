package com.healthmonitoringapi.dto;

import java.io.Serializable;

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
	private String street;
	private String city;
	private String district;
	private String state;
	private String number;
	private String zipcode;
	private String description;

	void parse(Address entity) {
		this.street = entity.getStreet();
		this.city = entity.getCity();
		this.district = entity.getDistrict();
		this.state = entity.getState();
		this.number = entity.getNumber();
		this.zipcode = entity.getZipcode();
		this.description = entity.getDescription();
	}

}
