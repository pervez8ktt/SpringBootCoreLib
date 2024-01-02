package com.knitkota.core.exception;

import java.util.function.Supplier;

import com.knitkota.core.config.Translator;
import com.knitkota.core.model.MessageModel;



public class InValidInputFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private MessageModel messageModel;

	public InValidInputFormatException() {
		this.messageModel = new MessageModel("Invalid request");
	}
	
	public InValidInputFormatException(String message, Object...params) {
		super();
		this.messageModel = new MessageModel(message, params);
	}
	
	public void setMessageModel(String message, Object...params) {
		this.messageModel = new MessageModel(message, params);
	}

	public MessageModel getMessageModel() {
		return messageModel;
	}

	@Override
	public String getMessage() {
		
		return Translator.messageTranslator(messageModel);
		
		
	}
	
	public static Supplier<InValidInputFormatException> createSupplier(String r, Object...params) {
		Supplier<InValidInputFormatException> exception = () -> new InValidInputFormatException(r, params);
		return exception;
	}
}
