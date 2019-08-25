package com.healthmonitoringapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.InfantDTO;
import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.entities.Parent;
import com.healthmonitoringapi.services.InfantService;

@RestController
@RequestMapping("/infant")
public class InfantController {

	@Autowired
	private InfantService infantService;

	@RequestMapping(method = RequestMethod.GET)
	public List<InfantDTO> findAll() {
		List<InfantDTO> infantsDTO = new ArrayList<>();
		try {
			List<Infant> infants = infantService.findAll();
			if (infants != null && !infants.isEmpty()) {
				for (Infant infant : infants) {
					InfantDTO infantDTO = new InfantDTO();
					infantDTO.parse(infant);
					infantsDTO.add(infantDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infantsDTO;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<Boolean> save(@PathVariable Integer id, InfantDTO infantDTO) {
		Boolean status = true;
		HttpStatus httpStatus = HttpStatus.CREATED;
		try {
			Infant infant = new Infant();
			infant.parse(infantDTO);
			infant.setParent(new Parent(id));
			infantService.save(infant);
		} catch (Exception e) {
			status = false;
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<Boolean> response = new ResponseEntity<>(status, httpStatus);
		return response;
	}
}
