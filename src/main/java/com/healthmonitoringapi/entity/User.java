package com.healthmonitoringapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthmonitoringapi.dto.UserDTO;
import com.healthmonitoringapi.util.BCryptUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(exclude = "parent")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4887746410344559969L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private Integer id;
	@Column(name = "email")
	private String email;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@JoinColumn(name = "idparent", referencedColumnName = "idparent")
	@OneToOne()
	private Parent parent;

	public void parse(UserDTO userDTO) {
		this.email = userDTO.getEmail();
		this.username = userDTO.getUsername();
		this.password = BCryptUtil.getHash(userDTO.getPassword());
		this.parent = new Parent();
		this.parent.parse(userDTO.getParent());
	}

}
