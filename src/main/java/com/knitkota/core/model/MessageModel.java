package com.knitkota.core.model;


public class MessageModel {

	private String message;
	
	private Object[] params;

	
	
	public MessageModel(String message, Object... params) {
		super();
		this.message = message;
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public Object[] getParams() {
		return params;
	}

}
