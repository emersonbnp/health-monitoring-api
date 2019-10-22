package com.healthmonitoringapi.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.healthmonitoringapi.util.Response;

public class BasicController <T> {

	public ResponseEntity<Response<T>> error(BindingResult result) {
		Response<T> response = new Response<T>(result.getAllErrors().stream().map(x -> {
			return x.getDefaultMessage();
		}).collect(Collectors.toList()));
		return new ResponseEntity<Response<T>>(response, HttpStatus.BAD_REQUEST); 
	}
	
}
