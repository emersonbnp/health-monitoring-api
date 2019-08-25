package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.healthmonitoringapi.entities.Infant;

public class InfantDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	private BigDecimal weight;
	private String device;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String street;
	private String city;
	private String district;
	private String state;
	private String number;
	private String zipcode;
	private String description;
	
	public InfantDTO () {}

	public InfantDTO(Integer id, String firstName, String lastName, LocalDate birthday, BigDecimal weight,
			String device) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.weight = weight;
		this.device = device;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void parse(Infant infant) {
		this.id = infant.getId();
		this.birthday = infant.getBirthday();
		this.device = infant.getDevice();
		this.firstName = infant.getFirstName();
		this.lastName = infant.getLastName();
		this.description = infant.getAddress().getDescription();
	}
}
