package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.entities.Parent;

public class ParentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate birthday;
	private List<InfantDTO> infants;

	public ParentDTO(String firstName, String lastName, LocalDate birthday, List<InfantDTO> infants) {
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

	public List<InfantDTO> getInfants() {
		return infants;
	}

	public void setInfants(List<InfantDTO> infants) {
		this.infants = infants;
	}
	
	public Parent shallowMapToEntity () {
		return new Parent(firstName, lastName, birthday, null);
	}
	
	public Parent deepMapToEntity() {
		List<Infant> infants = new ArrayList<Infant>();
		if (infants != null && !infants.isEmpty()) {
			infants.forEach(infantDTO -> {
				Infant infant = new Infant(infantDTO.getFirstName(), infantDTO.getLastName(), 
						infantDTO.getBirthday(), infantDTO.getWeight());
				infants.add(infant);
			});
		}
		Parent parent = new Parent(firstName, lastName, birthday, infants);
		return parent;
	}

}
