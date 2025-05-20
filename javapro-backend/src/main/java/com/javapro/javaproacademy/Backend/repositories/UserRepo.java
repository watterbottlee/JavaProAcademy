package com.javapro.javaproacademy.Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javapro.javaproacademy.Backend.entities.User;
import com.javapro.javaproacademy.Backend.payloads.ApiResponse;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	User findByName(String name);

}
