package com.healthmonitoringapi.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne()
	@JoinColumn(name = "parent", referencedColumnName = "id")
	private Parent parent;

	public Infant () {}
	
	public Infant(String firstName, String lastName, LocalDate birthday, BigDecimal weight) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InfantDTO shallowMap() {
		return new InfantDTO(firstName, lastName, birthday, weight);
	}
	
}
