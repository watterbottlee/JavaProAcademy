package com.javapro.javaproacademy.Backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class LoginResponse {
	
	private String message;
	private boolean status;

}
