package com.healthmonitoringapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.service.ParentService;
import com.healthmonitoringapi.util.Response;

@RestController
@RequestMapping("/parent")
public class ParentController extends BasicController<ParentDTO> {

	@Autowired
	private ParentService parentService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<ParentDTO>> get(@PathVariable Integer id) {
		ParentDTO parentDTO = new ParentDTO();
		Parent parent = parentService.findById(id);
		parentDTO.parse(parent);
		return new ResponseEntity<Response<ParentDTO>>(new Response<ParentDTO>(parentDTO), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Response<ParentDTO>> save(@Valid @RequestBody ParentDTO parentDTO, BindingResult result) {
		if (result.getAllErrors().isEmpty()) {
			Parent parent = new Parent();
			parent.parse(parentDTO);
			
			parentDTO = new ParentDTO();
			parentDTO.parse(parentService.save(parent));
			return new ResponseEntity<Response<ParentDTO>>(new Response<ParentDTO>(parentDTO), HttpStatus.CREATED);
		} else {
			return error(result);
		}
	}
}
