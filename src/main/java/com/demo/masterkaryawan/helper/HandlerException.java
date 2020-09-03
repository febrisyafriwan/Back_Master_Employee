package com.demo.masterkaryawan.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Custom Exception Handler
 */

@ControllerAdvice
public class HandlerException {
	// debug
	private static final Logger LOGGER = LoggerFactory.getLogger(HandlerException.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> allErrorHandling(Exception e, HttpServletRequest request,
			HttpServletResponse response) {
		if (e instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<CommonResponse> NotFoundResponse(NotFoundException e) {
		LOGGER.info(e.getMessage());
		LOGGER.error(e.getMessage());
		LOGGER.warn(e.getMessage());
		LOGGER.info(e.getMessage());
		LOGGER.debug(e.getMessage());
		return new ResponseEntity<CommonResponse>(new CommonResponse(e.getstatus(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
}