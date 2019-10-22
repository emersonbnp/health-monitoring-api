package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.entity.Parent;

import lombok.Data;

@Data
public class ParentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	@NotNull(message = "First name can't be empty")
	@Length(min = 3, max = 32, message = "First name should contain between 6 and 32 characters")
	private String firstName;
	@NotNull(message = "Last name can't be empty")
	@Length(min = 2, max = 32, message = "Last name should contain between 6 and 32 characters")
	private String lastName;
	@NotNull(message = "Phone can't be empty")
	@Length(min = 9, max = 18, message = "Phone should contain more between 9 and 18 numbers")
	private String phone;
	@NotNull(message = "User ID can't be empty")
	private String userID;
	private List<InfantDTO> infants;

	public void parse(Parent parent) {
		this.codigo = parent.getId();
		this.firstName = parent.getFirstName();
		this.lastName = parent.getLastName();
		this.phone = parent.getPhone();
		this.userID = parent.getUserID();
		if (parent.getInfants() != null && !parent.getInfants().isEmpty()) {
			this.infants = new ArrayList<>();
			for (Infant infant : parent.getInfants()) {
				InfantDTO infantDTO = new InfantDTO();
				infantDTO.parse(infant);
				infants.add(infantDTO);
			}
		}
	}

}