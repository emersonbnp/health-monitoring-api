package com.healthmonitoringapi.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitoringapi.entities.VitalSign;

import utils.LatestSign;

@RestController
public class GenericController {

	@RequestMapping(method = RequestMethod.GET, value = "/vital-sign/{device}")
	public ResponseEntity<VitalSign> save(@PathVariable String device) {
		VitalSign vitalSign = null;
		HttpStatus httpStatus = HttpStatus.ACCEPTED;
		try {
			Map<String, VitalSign> latest = LatestSign.getInstance();
			if (latest.containsKey(device)) {
				vitalSign = latest.get(device);
			} else {
				httpStatus = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<VitalSign> response = new ResponseEntity<VitalSign>(vitalSign, httpStatus);
		return response;
	}
}
