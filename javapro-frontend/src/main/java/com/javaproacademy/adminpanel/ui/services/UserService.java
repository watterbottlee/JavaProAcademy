package com.javaproacademy.adminpanel.ui.services;

import com.javaproacademy.adminpanel.ui.ApiResponseObjects.LoginResponseObj;
import com.javaproacademy.adminpanel.ui.FDOs.LoginFdo;
import com.javaproacademy.adminpanel.ui.FDOs.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserService {
    private final RestTemplate restTemplate= new RestTemplate();
    private final String CREATE_USER_API="http://localhost:8080/api/users";
    private final String GET_USERS_API="http://localhost:8080/api/users/getallusers";
    private final String LOGIN_URL="http://localhost:8080/api/users/login";

    public User createUser(User user){
        return restTemplate.postForObject(CREATE_USER_API, user, User.class);
    }
    public LoginResponseObj Login(LoginFdo loginFdo){
        return restTemplate.postForObject(LOGIN_URL,loginFdo, LoginResponseObj.class);
    }
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                GET_USERS_API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );
        return response.getBody();
    }
}
