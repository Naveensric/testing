package com.testing.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlleResourceNotFoundException(
									ResourceNotFoundException ex,WebRequest req){
		ErrorDetails error = new ErrorDetails(new Date()
								,ex.getMessage()
								,req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handlleAllInternalException(
									Exception ex,WebRequest req){
		ErrorDetails error = new ErrorDetails(new Date()
								,ex.getMessage()
								,req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
