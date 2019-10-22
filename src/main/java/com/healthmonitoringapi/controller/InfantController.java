package com.healthmonitoringapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.InfantDTO;
import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.service.InfantService;
import com.healthmonitoringapi.util.Response;

@RestController
@RequestMapping("/infant")
public class InfantController extends BasicController<InfantDTO> {

	@Autowired
	private InfantService infantService;

	@GetMapping
	public ResponseEntity<Response<List<InfantDTO>>> findAll() {
		List<InfantDTO> infantsDTO = new ArrayList<>();

		List<Infant> infants = infantService.findAll();
		if (infants != null && !infants.isEmpty()) {
			for (Infant infant : infants) {
				InfantDTO infantDTO = new InfantDTO();
				infantDTO.parse(infant);
				infantsDTO.add(infantDTO);
			}
		}
		return new ResponseEntity<Response<List<InfantDTO>>>(new Response<List<InfantDTO>>(infantsDTO), HttpStatus.OK);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<Response<InfantDTO>> save(@PathVariable Integer id, @Valid InfantDTO infantDTO,
			BindingResult result) {
		if (result.getAllErrors().isEmpty()) {
			Infant infant = new Infant();
			infant.parse(infantDTO);
			infant.setParent(new Parent(id));
			return new ResponseEntity<Response<InfantDTO>>(new Response<InfantDTO>(infantDTO), HttpStatus.CREATED);
		} else {
			return error(result);
		}
	}
}
