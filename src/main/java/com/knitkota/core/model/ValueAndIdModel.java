package com.knitkota.core.model;

public class ValueAndIdModel<T> {

	private Long id;
	private T title;

	public ValueAndIdModel(Long id, T title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T getTitle() {
		return title;
	}

	public void setTitle(T title) {
		this.title = title;
	}

}
