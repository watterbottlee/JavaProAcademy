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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		// Encode password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		// Encode password if it's being updated
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}

		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = this.userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user", "email", email));
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
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public LoginResponse verifyUser(LoginDto loginDto) {
		String email = loginDto.getEmail();
		Optional<User> userOptional = this.userRepo.findByEmail(email);

		if (userOptional.isEmpty()) {
			System.out.println("User not found with email: " + email);
			return new LoginResponse("User does not exist", false, null, null, null, null);
		}

		User user = userOptional.get();

		try {
			// Use only Spring Security's Authentication Manager for authentication
			// Note: The authentication manager expects the username field to match what's in UserDetailsService
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, loginDto.getPassword()));

			if (authentication.isAuthenticated()) {
				// Generate JWT token
				String jwtToken = jwtService.generateToken(email);

				return new LoginResponse(
						"Login successful",
						true,
						jwtToken,
						user.getId(),
						user.getName(),
						user.getEmail());
			} else {
				return new LoginResponse("Authentication failed", false, null, null, null, null);
			}
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return new LoginResponse("Wrong password", false, null, null, null, null);
		}
	}
}
