package com.javapro.javaproacademy.Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javapro.javaproacademy.Backend.payloads.ApiResponse;
import com.javapro.javaproacademy.Backend.payloads.UserDto;
import com.javapro.javaproacademy.Backend.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	//update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(
			@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
		UserDto updateUserDto = this.userService.updateUser(userDto, uid);
		return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
	}
	//delete user
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}
	//get user by id
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid){
		UserDto gotUser = this.userService.getUserById(uid);
		return new ResponseEntity<>(gotUser, HttpStatus.OK);
	}
	//get all users
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

}
