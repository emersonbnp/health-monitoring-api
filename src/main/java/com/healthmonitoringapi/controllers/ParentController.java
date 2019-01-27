package com.healthmonitoringapi.controllers;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.entities.Parent;

@RestController
@RequestMapping("/parent")
public class ParentController {

//	@Autowired
//	private ParentService parentService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String test() {
        return "Hello Controller - Test";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> save(ParentDTO parentDTO) {
		Boolean status = true;
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			Parent parent = parentDTO.shallowMapToEntity();
//			parentService.save(parent);
		} catch (Exception e) {
			status = false;
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<Boolean> response = new ResponseEntity<>(status, httpStatus);
		return response;
	}
}
