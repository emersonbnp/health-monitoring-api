package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.entities.Parent;

public class ParentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private List<InfantDTO> infants;

	public ParentDTO(Integer codigo, String firstName, String lastName, String email, List<InfantDTO> infants) {
		super();
		this.codigo = codigo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.infants = infants;
	}

	public ParentDTO(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public ParentDTO() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public List<InfantDTO> getInfants() {
		return infants;
	}

	public void setInfants(List<InfantDTO> infants) {
		this.infants = infants;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Parent shallowMapToEntity() {
		return new Parent(firstName, lastName, email, null);
	}

//	public Parent deepMapToEntity() {
//		List<Infant> infants = new ArrayList<Infant>();
//		if (infants != null && !infants.isEmpty()) {
//			infants.forEach(infantDTO -> {
//				Infant infant = new Infant(infantDTO.getFirstName(), infantDTO.getLastName(), infantDTO.getBirthday(),
//						infantDTO.getWeight(), infantDTO.getDevice(), infantDTO.getLatitude(), infantDTO.getLatitude(),
//						infantDTO.getStreet(), infantDTO.getState(), infantDTO.getDistrict(), infantDTO.getZipcode(),
//						infantDTO.getCity(), infantDTO.getNumber(), infantDTO.getDescription());
//				infants.add(infant);
//			});
//		}
//		Parent parent = new Parent(firstName, lastName, email, infants);
//		return parent;
//	}
	
	public void parse(Parent parent) {
		this.codigo = parent.getId();
		this.email = parent.getEmail();
		this.firstName = parent.getFirstName();
		this.lastName = parent.getLastName();
		this.phone = parent.getPhone();
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