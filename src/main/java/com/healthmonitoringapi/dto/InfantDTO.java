package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.healthmonitoringapi.entity.Infant;

import lombok.Data;

@Data
public class InfantDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = "Infant's first name should not be empty")
	@Length(min = 3, max = 32, message = "Infant's first name should have between 3 and 32 letters")
	private String firstName;
	@NotNull(message = "Infant's last name should not be empty")
	@Length(min = 3, max = 32, message = "Infant's last name should have between 3 and 32 letters")
	private String lastName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	@NotNull(message = "Infant's weight name should not be empty")
	private BigDecimal weight;
	@NotNull(message = "Device name should not be empty")
	@Length(min = 10, max = 20, message = "Device should have between 10 and 20 letters")
	private String device;
	@NotNull(message = "Address info must not be empty")
	@JsonInclude(Include.NON_NULL)
	private AddressDTO address;

	public void parse(Infant infant) {
		this.id = infant.getId();
		this.birthday = infant.getBirthday();
		this.weight = infant.getWeight();
		this.device = infant.getDevice();
		this.firstName = infant.getFirstName();
		this.lastName = infant.getLastName();
		this.address = new AddressDTO();
		this.address.parse(infant.getAddress());
	}
}
