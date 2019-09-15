package com.healthmonitoringapi.dto;

import java.io.Serializable;

public class SignUpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String userid;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ParentDTO getParent() {
		return new ParentDTO(firstName, lastName, email, phone);
	}

	public void setParent(ParentDTO parent) {
		this.email = parent.getEmail();
		this.firstName = parent.getFirstName();
		this.lastName = parent.getLastName();
		this.phone = parent.getPhone();
	}

}
