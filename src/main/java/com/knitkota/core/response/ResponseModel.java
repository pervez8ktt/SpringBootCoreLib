package com.knitkota.core.response;

public class ResponseModel<T> {

	private T data;
	private Status status;
	private String message;

	protected ResponseModel() {
		
	}
	
	public T getData() {
		return data;
	}

	protected void setData(T data) {
		this.data = data;
	}

	
	public Status getStatus() {
		return status;
	}

	protected void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}
	
	public enum Status{
		SUCCESS, FAIL
	}

}
