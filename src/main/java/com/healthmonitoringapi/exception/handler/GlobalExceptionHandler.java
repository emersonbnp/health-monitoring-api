package com.healthmonitoringapi.exception.handler;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.healthmonitoringapi.exception.CustomException;
import com.healthmonitoringapi.util.Response;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CustomException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String error = ex.getCause().getMessage();
		Response<Object> response = new Response<Object>();
		response.setErrors(Arrays.asList(new String [] {error}));
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGenericConflict(Exception ex, WebRequest request) {
		String error = ex.getMessage();
		Response<Object> response = new Response<Object>();
		response.setErrors(Arrays.asList(new String [] {error}));
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}