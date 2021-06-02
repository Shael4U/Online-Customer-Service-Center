package com.capgemini.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.entities.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request method not supported");
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Media not supported");
		details.add(ex.getMessage());
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Path Variable is missing");
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request param is missing");
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Mismatch of type");
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request body is not Readable");
		ErrorDetails errDetails = new ErrorDetails(message, details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errDetails);
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	protected ResponseEntity<Object> handleInvalidCredentialException(InvalidCredentialException ex)
	{
	
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Failed to Login");
		ErrorDetails errDetails = new ErrorDetails(message, details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errDetails);
	}
	
	@ExceptionHandler(DuplicateCustomerException.class)
	protected ResponseEntity<Object> handleDuplicateCustomerException(DuplicateCustomerException ex)
	{
	
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Already Exists");
		ErrorDetails errDetails = new ErrorDetails(message, details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errDetails);
	}

}
