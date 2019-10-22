package com.healthmonitoringapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.UserDTO;
import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.service.UserService;
import com.healthmonitoringapi.util.Response;

@RestController
@RequestMapping("/user")
public class UserController extends BasicController<UserDTO> {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Response<UserDTO>> save(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
		if (result.getAllErrors().isEmpty()) {
			User user= new User();
			user.parse(userDTO);
			userService.save(user);
			return new ResponseEntity<Response<UserDTO>>(new Response<UserDTO>(userDTO), HttpStatus.CREATED);
		} else {
			return error(result);
		}
	}
}
