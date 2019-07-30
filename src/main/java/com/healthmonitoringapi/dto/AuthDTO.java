package com.healthmonitoringapi.dto;

import java.io.Serializable;

public class AuthDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String userid;
	private ParentDTO parent;

	public AuthDTO(String username, String password, String userid, ParentDTO parent) {
		super();
		this.username = username;
		this.password = password;
		this.userid = userid;
		this.parent = parent;
	}

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

	public ParentDTO getParent() {
		return parent;
	}

	public void setParent(ParentDTO parent) {
		this.parent = parent;
	}

}
