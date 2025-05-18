package com.javapro.javaproacademy.Backend.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class ApiResponse {
	private String messege;
	private Boolean succcess;
}

