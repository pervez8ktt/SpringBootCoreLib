package com.knitkota.core.model;

import java.sql.Timestamp;

public class DateResponseModel {

	private Long dateInMilliseconds;
	private Timestamp dateTimestamp;

	public DateResponseModel(Long epoch) {
		if(epoch!=null) {
			dateTimestamp = new Timestamp(epoch);
			dateInMilliseconds = epoch;
		}
	}
	
	public Long getDateInMilliseconds() {
		return dateInMilliseconds;
	}

	public void setDateInMilliseconds(Long dateInMilliseconds) {
		this.dateInMilliseconds = dateInMilliseconds;
	}

	public Timestamp getDateTimestamp() {
		return dateTimestamp;
	}

	public void setDateTimestamp(Timestamp dateTimestamp) {
		this.dateTimestamp = dateTimestamp;
	}

}
