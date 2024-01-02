package com.knitkota.core.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.knitkota.core.config.Translator;
import com.knitkota.core.response.ResponseModel.Status;

public class ResponseBuilder<T> {

	private Status status; 
	private T data; 
	private String message;

	public ResponseBuilder<T> setStatus(Status status) {
		this.status=status;
		return this;
	}
	
	public ResponseBuilder<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	public ResponseBuilder<T> setMessage(String message, Object... params) {
		this.message = Translator.messageTranslator(message, params);
		
		return this;
	}
	
	public ResponseEntity<ResponseModel<T>> build() {
		
		if(message==null) {
			this.message="";
		}
		
		if(status==null) {
			status=Status.SUCCESS;
		}
		
		ResponseModel<T> responseModel = new ResponseModel<>();
		
		responseModel.setData(data);
		responseModel.setMessage(message);
		responseModel.setStatus(status);
		
		return new ResponseEntity<ResponseModel<T>>(responseModel, HttpStatus.OK);
	}
	
}
