package com.javapro.javaproacademy.Backend.services;

import java.util.List;

import com.javapro.javaproacademy.Backend.payloads.UserDto;

public interface UserService{
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Integer userId);
	UserDto getUserById(Integer userId);
	void deleteUser(Integer userId);
	List<UserDto> getAllUsers();
}
