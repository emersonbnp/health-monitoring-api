package com.healthmonitoringapi.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parent", schema = "postgres")
public class Parent extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_generator")
	@SequenceGenerator(name = "parent_generator", sequenceName = "seq_parent")
	private Integer id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "birthday")
	private LocalDate birthday;
	@OneToMany(mappedBy = "parent")
	private List<Infant> infants;
	
	public Parent () {}

	public Parent(String firstName, String lastName, LocalDate birthday, List<Infant> infants) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.infants = infants;
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

	public List<Infant> getInfants() {
		return infants;
	}

	public void setInfants(List<Infant> infants) {
		this.infants = infants;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
