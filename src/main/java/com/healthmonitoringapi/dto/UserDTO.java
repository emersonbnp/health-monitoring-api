package com.healthmonitoringapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3063009554101058648L;
	private Integer id;
	@NotNull(message = "Email can't be empty")
	@Email(message = "Email can't be empty")
	private String email;
	@NotNull(message = "Username can't be empty")
	@Length(min = 3, max = 32, message = "Username should contain between 3 and 32 characters")
	private String username;
	@NotNull(message = "Password can't be empty")
	@Length(min = 6, max = 32, message = "Password should contain between 6 and 32 characters")
	private String password;
	@NotNull(message = "Parent info is necessary")
	private ParentDTO parent;

}
