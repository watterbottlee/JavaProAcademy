package com.javapro.javaproacademy.Backend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="username must be min of 4 chars")
	private String name;
	
	@Email(message="your email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=20,message="password must be min of 3 cgars and max of 20 chars")
	private String password;

}
