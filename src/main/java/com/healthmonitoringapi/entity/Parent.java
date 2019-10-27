package com.healthmonitoringapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthmonitoringapi.dto.ParentDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parent")
@Data
@EqualsAndHashCode()
public class Parent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6255620678102218300L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idparent")
	private Integer id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "userid")
	private String userID;
	@Column(name = "phone")
	private String phone;
	@OneToMany(mappedBy = "parent")
	private List<Infant> infants;
	@OneToOne(mappedBy = "parent")
	private User user;

	public Parent() {
	}

	public Parent(Integer id) {
		this.id = id;
	}

	public void parse(ParentDTO parentDTO) {
		this.firstName = parentDTO.getFirstName();
		this.lastName = parentDTO.getLastName();
		this.phone = parentDTO.getPhone();
		this.userID = parentDTO.getUserID();
	}

}
