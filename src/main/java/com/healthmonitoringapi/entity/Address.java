package com.healthmonitoringapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthmonitoringapi.dto.AddressDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "address")
@Data
@EqualsAndHashCode(callSuper = false)
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501434443126913173L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idaddress")
	private Integer id;
	@Column(name = "latitude")
	private BigDecimal latitude;
	@Column(name = "longitude")
	private BigDecimal longitude;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "street")
	private String street;
	@Column(name = "district")
	private String district;
	@Column(name = "zipcode")
	private String zipcode;
	@Column(name = "number")
	private String number;
	@Column(name = "description")
	private String description;
	@JoinColumn(name = "idinfant", referencedColumnName = "idinfant")
	@OneToOne()
	private Infant infant;

	public void parse(AddressDTO addressDTO, Infant infant) {
		this.setInfant(infant);
		this.parse(addressDTO);
	}

	public void parse(AddressDTO addressDTO) {
		this.setCity(addressDTO.getCity());
		this.setDescription(addressDTO.getDescription());
		this.setDistrict(addressDTO.getDistrict());
		this.setNumber(addressDTO.getNumber());
		this.setState(addressDTO.getState());
		this.setStreet(addressDTO.getStreet());
		this.setZipcode(addressDTO.getZipcode());
	}
}
