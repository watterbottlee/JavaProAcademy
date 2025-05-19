package com.javaproacademy.adminpanel.ui.ApiResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseObj {

    private String message;
    private boolean status;
    private String name;

}
