package com.knitkota.core.exception.power;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.knitkota.core.exception.AccessDeniedException;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	protected final static Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
		logger.error("error", ex);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
	public final ResponseEntity<ExceptionResponse> accessDeniedException(Exception ex, WebRequest request)
			throws Exception {
		AccessDeniedException accessDeniedException = new AccessDeniedException();
		return customResponseException(accessDeniedException, request);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> usernameNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		AccessDeniedException accessDeniedException = new AccessDeniedException();
		return customResponseException(accessDeniedException, request);
	}

	@ExceptionHandler({ CustomResponseException.class })
	public final ResponseEntity<ExceptionResponse> customResponseException(CustomResponseException ex,
			WebRequest request) {
		// String reasone = ex.getReason();
		// HttpStatus responseStatus = ex.getHttpStatus();
		// String desc = request.getDescription(false);
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getReason(), ex.getHttpStatus().value(),
				request.getDescription(false));
		logger.error("api-error", ex);
		// return new ResponseEntity<ExceptionResponse>(response, ex.getHttpStatus());

		return new ResponseEntity<ExceptionResponse>(response, ex.getHttpStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
				request.getDescription(false));
		// logger.info("error", ex);
		String stacktrace = ExceptionUtils.getStackTrace(ex);

		logger.info("bad-request: " + stacktrace);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		// return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}
}
