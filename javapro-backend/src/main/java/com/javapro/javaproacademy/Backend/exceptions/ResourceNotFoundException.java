package com.javapro.javaproacademy.Backend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldValue;
	String email;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue){
		super(String.format("%s not found with %s: %s",resourceName, fieldName, fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String email){
		super(String.format("%s not found with %s: %s",resourceName, fieldName, email));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.email=email;
	}
}

