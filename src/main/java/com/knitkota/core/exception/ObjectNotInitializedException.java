package com.knitkota.core.exception;


public class ObjectNotInitializedException extends InValidInputFormatException {

	private static final long serialVersionUID = 1L;

	private Class<?> classObj;

	public ObjectNotInitializedException(Class<?> classObj) {

		super("Object not initialized", classObj.getName());

		this.classObj = classObj;

	}

	public Class<?> getClassObj() {
		return classObj;
	}

}
