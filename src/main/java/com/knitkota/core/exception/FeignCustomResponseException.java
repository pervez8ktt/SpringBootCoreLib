package com.knitkota.core.exception;

import org.springframework.http.HttpStatus;

public class FeignCustomResponseException extends Exception {

	private HttpStatus status;

	public FeignCustomResponseException(HttpStatus status, String reason) {
		super(reason);
		this.status = status;
		
	}

	public HttpStatus getStatus() {
		return status;
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

}
