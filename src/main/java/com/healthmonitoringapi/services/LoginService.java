package com.healthmonitoringapi.services;

import com.healthmonitoringapi.dto.AuthDTO;
import com.healthmonitoringapi.dto.SignUpDTO;
import com.healthmonitoringapi.entities.Parent;

public interface LoginService {

	public Parent login(AuthDTO authDTO);

	public Parent signUp(SignUpDTO signupDTO);

}
