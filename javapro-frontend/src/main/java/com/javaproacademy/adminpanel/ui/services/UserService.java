package com.javaproacademy.adminpanel.ui.services;

import com.javaproacademy.adminpanel.ui.FDOs.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserService {
    private final RestTemplate restTemplate= new RestTemplate();
    private final String CREATE_USER_API="http://localhost:8080/api/users";

    public User createUser(User user){
        return restTemplate.postForObject(CREATE_USER_API, user, User.class);
    }
}
