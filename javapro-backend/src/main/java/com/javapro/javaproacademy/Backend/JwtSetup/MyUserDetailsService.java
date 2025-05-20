package com.javapro.javaproacademy.Backend.JwtSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javapro.javaproacademy.Backend.entities.User;
import com.javapro.javaproacademy.Backend.repositories.UserRepo;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Since we're using email for authentication, load user by email
        Optional<User> userOptional = userRepo.findByEmail(username);

        if (userOptional.isEmpty()) {
            System.out.println("User not found with email: " + username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new UserPrincipal(userOptional.get());
    }
}
