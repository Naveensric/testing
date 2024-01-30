package com.testing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private String resourcesName;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFoundException(String resourcesName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourcesName,fieldName,fieldValue));
		//post not found with id : 1
		this.resourcesName = resourcesName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
