package com.healthmonitoringapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.AuthDTO;
import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.dto.SignUpDTO;
import com.healthmonitoringapi.entities.Parent;
import com.healthmonitoringapi.repositories.ParentRepository;
import com.healthmonitoringapi.services.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ParentRepository parentRepository;

	/*
	 * Very basic login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ParentDTO login(AuthDTO authDTO) {
		Parent parent = loginService.login(authDTO);
		if (!authDTO.getUserid().equals(parent.getUserID())) {
			parent.setUserID(authDTO.getUserid());
			parentRepository.save(parent);
		}
		return parent.shallowMap();
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ParentDTO signup(SignUpDTO signupDTO) {
		Parent parent = loginService.signUp(signupDTO);
		return parent.shallowMap();
	}

}
