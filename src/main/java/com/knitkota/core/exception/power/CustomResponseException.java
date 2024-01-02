package com.knitkota.core.exception.power;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.knitkota.core.config.Translator;
import com.knitkota.core.exception.FeignCustomResponseException;
import com.knitkota.core.exception.InValidInputFormatException;
import com.knitkota.core.model.MessageModel;




public class CustomResponseException extends ResponseStatusException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpStatus httpStatus;
	
	
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public CustomResponseException() {
		this("Invalid request");
	}
	
	public CustomResponseException(InValidInputFormatException e) {
		this(e.getMessageModel());
		
	}
	
	public CustomResponseException(MessageModel messageModel) {
		this(messageModel.getMessage(), messageModel.getParams());
		
	}
	
	public CustomResponseException(String message, Object... param) {
		this(HttpStatus.UNPROCESSABLE_ENTITY, message, param);
		
	}

	public CustomResponseException(HttpStatus status, String message, Object... param) {
		super(status, Translator.messageTranslator(message,param));
		this.httpStatus = status;
		
	}
	
	public CustomResponseException(HttpStatus status, String message) {
		super(status, message);
		this.httpStatus = status;
		// TODO Auto-generated constructor stub
	}

	public CustomResponseException(FeignCustomResponseException e) {
		super(e.getStatus(), e.getMessage());
		this.httpStatus = e.getStatus();
	}

	/*
	 * public CustomResponseException(HttpStatus status, String message) {
	 * super(status, message); this.httpStatus = status; 
	 * constructor stub }
	 */

	public static Supplier<CustomResponseException> createSupplier(String r, Object...params) {
		Supplier<CustomResponseException> exception = () -> new CustomResponseException(r, params);
		return exception;
	}
	
}
