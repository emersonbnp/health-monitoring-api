package com.healthmonitoringapi.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "postgres")
public class Address extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
	@SequenceGenerator(name = "address_generator", sequenceName = "seq_address")
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Infant getInfant() {
		return infant;
	}

	public void setInfant(Infant infant) {
		this.infant = infant;
	}

}
