package com.healthmonitoringapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmonitoringapi.dto.AuthDTO;
import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.dto.SignUpDTO;
import com.healthmonitoringapi.entities.Parent;
import com.healthmonitoringapi.entities.User;
import com.healthmonitoringapi.repositories.ParentRepository;
import com.healthmonitoringapi.repositories.UserRepository;
import com.healthmonitoringapi.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParentRepository parentRepository;

	@Override
	public Parent login(AuthDTO authDTO) {

		User user = this.userRepository.findByUsernameAndPassword(authDTO.getUsername(), authDTO.getPassword());
		Parent parent = null;
		if (user != null) {
			parent = user.getParent();
		}
		return parent;
	}

	@Override
	public Parent signUp(SignUpDTO signupDTO) {
		
		Parent parent = signupDTO.getParent().deepMapToEntity();
		parent = parentRepository.save(parent);
		
		User user = new User();
		user.setPassword(signupDTO.getPassword());
		user.setUsername(signupDTO.getUsername());
		user.setParent(parent);
		userRepository.save(user);
		
		return parent;
	}
}
