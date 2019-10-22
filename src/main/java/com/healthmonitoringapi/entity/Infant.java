package com.healthmonitoringapi.entity;

import java.io.Serializable;
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
import javax.persistence.Table;

import com.healthmonitoringapi.dto.InfantDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "infant")
@Data
@EqualsAndHashCode(callSuper = false)
public class Infant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 562894809465817449L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToOne(mappedBy = "infant", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Address address;

	public void parse(InfantDTO infantDTO) {
		this.id = infantDTO.getId();
		this.firstName = infantDTO.getFirstName();
		this.lastName = infantDTO.getLastName();
		this.birthday = infantDTO.getBirthday();
		this.device = infantDTO.getDevice();
		this.weight = infantDTO.getWeight();

		this.address = new Address();
		this.address.parse(infantDTO.getAddress(), this);

	}

}
