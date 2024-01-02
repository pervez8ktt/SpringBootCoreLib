package com.knitkota.core.response;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class CommonSearchRequestModel<T> {

	private T searchModel;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalRecords;
	private Long totalPages;

	public CommonSearchRequestModel() {
		pageNumber = 0;
		pageSize = 10;
	}

	public T getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(T searchModel) {
		this.searchModel = searchModel;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Long getTotalPages() {

		long pages = totalRecords / pageSize;

		if (totalRecords % pageSize > 0) {
			pages += 1;
		}

		return pages;

	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public <V> CommonSearchRequestModel<V> getResponse(V v, Long totalRecords) {
		CommonSearchRequestModel<V> commonSearchModel = new CommonSearchRequestModel<>();
		commonSearchModel.setPageNumber(getPageNumber());
		commonSearchModel.setPageSize(getPageSize());
		commonSearchModel.setTotalRecords(totalRecords);
		commonSearchModel.setSearchModel(v);

		return commonSearchModel;
	}

	public <V, T> CommonSearchRequestModel<List<T>> getResponse(List<V> content, long totalElements, Class<V> entity,
			Class<T> dto) {

		List<T> list = new ArrayList<T>();
		try {
			for (V v : content) {

				Constructor<T> cont = dto.getConstructor(entity);

				T t = cont.newInstance(v);
				list.add(t);
			}

		} catch (Exception e) {

		}

		return getResponse(list, totalElements);

	}

}
