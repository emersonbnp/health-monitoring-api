package com.healthmonitoringapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.healthmonitoringapi.entity.Infant;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfantDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	private BigDecimal weight;
	private String device;
	private AddressDTO address;

	public void parse(Infant infant) {
		this.id = infant.getId();
		this.birthday = infant.getBirthday();
		this.weight = infant.getWeight();
		this.device = infant.getDevice();
		this.firstName = infant.getFirstName();
		this.lastName = infant.getLastName();
	}
}
