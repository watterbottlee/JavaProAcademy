package com.javapro.javaproacademy.Backend.services;

import java.util.List;

import com.javapro.javaproacademy.Backend.payloads.LoginDto;
import com.javapro.javaproacademy.Backend.payloads.LoginResponse;
import com.javapro.javaproacademy.Backend.payloads.UserDto;

public interface UserService{
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Integer userId);
	UserDto getUserById(Integer userId);
	UserDto getUserByEmail(String email);
	LoginResponse verifyUser(LoginDto loginDto);
	void deleteUser(Integer userId);
	List<UserDto> getAllUsers();
}
