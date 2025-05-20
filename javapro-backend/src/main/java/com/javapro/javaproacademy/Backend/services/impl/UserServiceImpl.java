package com.javapro.javaproacademy.Backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javapro.javaproacademy.Backend.JwtSetup.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.javapro.javaproacademy.Backend.entities.User;
import com.javapro.javaproacademy.Backend.exceptions.ResourceNotFoundException;
import com.javapro.javaproacademy.Backend.payloads.LoginDto;
import com.javapro.javaproacademy.Backend.payloads.LoginResponse;
import com.javapro.javaproacademy.Backend.payloads.UserDto;
import com.javapro.javaproacademy.Backend.repositories.UserRepo;
import com.javapro.javaproacademy.Backend.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		return this.userToDto(user);
	}
	
	@Override
	public UserDto getUserByEmail(String email) {
		User user = this.userRepo.findByEmail(email)
				.orElseThrow(()-> new ResourceNotFoundException("user", "email", email));
		return this.userToDto(user);
	}
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user= this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	public UserDto userToDto(User user){
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	
	@Override
	public LoginResponse verifyUser(LoginDto logindto) {
		String email= logindto.getEmail();
		String password= logindto.getPassword();
		Optional<User> user = this.userRepo.findByEmail(email);
		if(user.isEmpty()) {
			return new LoginResponse("User does not exist",false,null,null,null,null);
		}else {
			if(user.get().getPassword().equals(password)) {
				Authentication authentication = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
				if(authentication.isAuthenticated()) {
					String jwtToken = jwtService.generateToken(logindto.getEmail());
					return new LoginResponse(
							"login successfull",
							true,
							jwtToken,
							user.get().getId(),
							user.get().getName(),
							user.get().getEmail());
				};
			}
			return new LoginResponse(
					"Wrong password",
					false,
					null,
					null,
					null,
					null);
		}
	}

}