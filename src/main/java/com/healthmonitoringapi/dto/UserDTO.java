package com.healthmonitoringapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.healthmonitoringapi.entity.User;

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
	@JsonInclude(Include.NON_NULL)
	private String password;
	@NotNull(message = "Parent info is necessary")
	private ParentDTO parent;
	
	public void parse(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		this.username = entity.getUsername();
		
		this.parent = new ParentDTO();
		this.parent.parse(entity.getParent());
	}

}
