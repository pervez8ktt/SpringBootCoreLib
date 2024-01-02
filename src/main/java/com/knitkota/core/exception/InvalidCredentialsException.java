package com.knitkota.core.exception;

import com.knitkota.core.exception.power.CustomResponseException;

public class InvalidCredentialsException extends CustomResponseException{

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid Credentials");
		
	}

	
	
}
