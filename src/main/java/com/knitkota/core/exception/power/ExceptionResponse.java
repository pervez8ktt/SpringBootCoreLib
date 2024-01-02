package com.knitkota.core.exception.power;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.knitkota.core.response.ResponseModel.Status;

/**
 * @author Claudemon Junior Simeon
 * @since 02/16/2019
 *
 */
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private int errorCode;
	private String details;
	private Status status;
	// private String path;
	private String stackTrace;

	public ExceptionResponse(Date timestamp, Exception exception, int errorCode, String details) {
		super();
		this.timestamp = timestamp;
		this.message = exception.getMessage();
		this.errorCode = errorCode;
		status = Status.FAIL;
		this.details = details;

		String stacktrace = ExceptionUtils.getStackTrace(exception);

		this.stackTrace = stacktrace;
		// this.path = "";
	}
	
	public ExceptionResponse(Date timestamp, String message, int errorCode, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorCode = errorCode;
		status = Status.FAIL;
		this.details = details;

		
		// this.path = "";
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

}
