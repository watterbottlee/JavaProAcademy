package com.javapro.javaproacademy.Backend.payloads;

import jakarta.annotation.Nullable;
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

	@Nullable
	private String jwtToken;
	@Nullable
	private Integer id;
	@Nullable
	private String name;
	@Nullable
	private String email;

}
