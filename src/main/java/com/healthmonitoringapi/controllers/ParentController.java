package com.healthmonitoringapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.entities.Parent;
import com.healthmonitoringapi.services.ParentService;

@RestController
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private ParentService parentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ParentDTO> get(@PathVariable Integer id) {
		ParentDTO parent = null;
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			parent = parentService.findById(id).shallowMap();
		} catch (Exception e) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<ParentDTO> response = new ResponseEntity<>(parent, httpStatus);
		return response;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> save(ParentDTO parentDTO) {
		Boolean status = true;
		HttpStatus httpStatus = HttpStatus.CREATED;
		try {
			Parent parent = parentDTO.shallowMapToEntity();
			parentService.save(parent);
		} catch (Exception e) {
			status = false;
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<Boolean> response = new ResponseEntity<>(status, httpStatus);
		return response;
	}
}
