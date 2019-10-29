package com.healthmonitoringapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.InfantDTO;
import com.healthmonitoringapi.entity.Infant;
import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.exception.EntityNotFoundException;
import com.healthmonitoringapi.exception.UserNotFoundException;
import com.healthmonitoringapi.service.InfantService;
import com.healthmonitoringapi.service.ParentService;
import com.healthmonitoringapi.util.Response;

@RestController
@RequestMapping("/infant")
public class InfantController extends BasicController<InfantDTO> {

	@Autowired
	private InfantService infantService;
	
	@Autowired
	private ParentService parentService;

	private static final Logger logger = LoggerFactory.getLogger(InfantController.class);

	@GetMapping
	public ResponseEntity<Response<List<InfantDTO>>> findByParent(
			@RequestParam(name = "parentId", required = true) Integer parentId,
			@RequestParam(name = "limit", defaultValue = "5", required = false) Integer limit,
			@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
			@RequestParam(name = "order_by", defaultValue = "asc", required = false) String order)
			throws UserNotFoundException {

		Parent parent = new Parent();
		parent.setId(parentId);

		logger.info("Looking for infants which parent is {}", parent.getId());

		Direction direction = order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;

		Sort sort = Sort.by(direction, "id");

		Pageable pageable = PageRequest.of(offset, limit, sort);

		List<InfantDTO> infantsDTO = new ArrayList<>();
		List<Infant> infants = infantService.findByParent(parent, pageable);
		for (Infant infant : infants) {
			InfantDTO infantDTO = new InfantDTO();
			infantDTO.parse(infant);
			infantsDTO.add(infantDTO);
		}
		return new ResponseEntity<Response<List<InfantDTO>>>(new Response<List<InfantDTO>>(infantsDTO), HttpStatus.OK);
	}

	@GetMapping(path = "/{parentId}/{infantId}")
	public ResponseEntity<Response<InfantDTO>> find(
			@PathVariable Integer parentId,
			@PathVariable Integer infantId)
			throws UserNotFoundException, EntityNotFoundException {
		
		Parent parent = parentService.findById(parentId);

		Infant infant = infantService.findByIdAndParent(infantId, parent);

		InfantDTO infantDTO = new InfantDTO();
		infantDTO.parse(infant);

		return new ResponseEntity<Response<InfantDTO>>(new Response<InfantDTO>(infantDTO), HttpStatus.OK);

	}

	@PostMapping(path = "/{parentId}")
	public ResponseEntity<Response<InfantDTO>> save(
			@PathVariable Integer parentId,
			@RequestBody @Valid InfantDTO infantDTO, 
			BindingResult result)
			throws UserNotFoundException, EntityNotFoundException {
		if (result.getAllErrors().isEmpty()) {

			Parent parent = parentService.findById(parentId);

			Infant infant = new Infant();
			infant.parse(infantDTO);
			infant.setParent(parent);

			infantDTO = new InfantDTO();
			infantDTO.parse(infantService.save(infant));
			return new ResponseEntity<Response<InfantDTO>>(new Response<InfantDTO>(infantDTO), HttpStatus.CREATED);
		} else {
			return error(result);
		}
	}
}
