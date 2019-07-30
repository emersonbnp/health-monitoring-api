package com.healthmonitoringapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.healthmonitoringapi.dto.InfantDTO;
import com.healthmonitoringapi.dto.ParentDTO;

@Entity
@Table(name = "parent", schema = "postgres")
public class Parent extends PersistentEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_generator")
	@SequenceGenerator(name = "parent_generator", sequenceName = "seq_parent")
	@Column(name = "idparent")
	private Integer id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "birthday")
	private String email;
	@Column(name = "userid")
	private String userID;
	@OneToMany(mappedBy = "parent")
	private List<Infant> infants;
	@OneToOne(mappedBy = "parent")
	private User user;

	public Parent() {
	}

	public Parent(Integer id) {
		setId(id);
	}

	public Parent(String firstName, String lastName, String email, List<Infant> infants) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ParentDTO shallowMap() {
		List<InfantDTO> infantsDTO = new ArrayList<InfantDTO>();
		if (getInfants() != null && !getInfants().isEmpty()) {
			this.getInfants().forEach(infant -> {
				InfantDTO infantDTO = new InfantDTO(infant.getId(), infant.getFirstName(), infant.getLastName(),
						infant.getBirthday(), infant.getWeight(), infant.getDevice());
				infantsDTO.add(infantDTO);
			});
		}
		return new ParentDTO(id, firstName, lastName, email, infantsDTO);
	}

}
