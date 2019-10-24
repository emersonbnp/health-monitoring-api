package com.healthmonitoringapi.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class JwtAuthenticationDTO {

	@NotNull(message = "Email can't be empty")
	@Email(message = "Invalid email format")
	private String email;
	@NotNull(message = "Password can't be empty")
	@Length(min = 6, max = 32, message = "Invalid password")
	private String password;

}
