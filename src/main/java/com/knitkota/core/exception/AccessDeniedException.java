package com.knitkota.core.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

import com.knitkota.core.exception.power.CustomResponseException;

import org.springframework.http.HttpStatus;





@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends CustomResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedException () {
		this("Invalid User");
	}
	
	public AccessDeniedException (String message, Object... param) {
		super(HttpStatus.UNAUTHORIZED,message,param);
	}
}
