package com.healthmonitoringapi.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.healthmonitoringapi.dto.InfantDTO;

@Entity
@Table(name = "infant", schema = "postgres")
public class Infant extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "infant_generator")
	@SequenceGenerator(name = "infant_generator", sequenceName = "seq_infant")
	@Column(name = "idinfant")
	private Integer id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "birthday")
	private LocalDate birthday;
	@Column(name = "weight")
	private BigDecimal weight;
	@Column(name = "device")
	private String device;
	@ManyToOne()
	@JoinColumn(name = "idparent", referencedColumnName = "idparent")
	private Parent parent;
	@OneToOne(mappedBy = "infant", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Address address;

	public Infant() {
	}

	public Infant(String firstName, String lastName, LocalDate birthday, BigDecimal weight, String device) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.weight = weight;
		this.device = device;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public InfantDTO shallowMap() {
	// return new InfantDTO(id, firstName, lastName, birthday, weight, device,
	// description);
	// }

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void parse(InfantDTO infantDTO) {
		this.id = infantDTO.getId();
		this.firstName = infantDTO.getFirstName();
		this.lastName = infantDTO.getLastName();
		this.birthday = infantDTO.getBirthday();
		this.device = infantDTO.getDevice();
		this.weight = infantDTO.getWeight();

		this.address = new Address();
		this.address.setInfant(this);
		this.address.setCity(infantDTO.getCity());
		this.address.setDescription(infantDTO.getDescription());
		this.address.setDistrict(infantDTO.getDistrict());
		this.address.setLatitude(infantDTO.getLatitude());
		this.address.setLongitude(infantDTO.getLongitude());
		this.address.setNumber(infantDTO.getNumber());
		this.address.setState(infantDTO.getState());
		this.address.setStreet(infantDTO.getStreet());
		this.address.setZipcode(infantDTO.getZipcode());
	}

}
