package com.knitkota.core.model;

public class CommonSingleObjectRequestModel<T> {

	private T object;

	public static <T> CommonSingleObjectRequestModel<T> initValue(T t) {
		CommonSingleObjectRequestModel<T> s = new CommonSingleObjectRequestModel<T>();
		
		s.setObject(t);
		
		return s;
		
	}
	
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	
	
}
