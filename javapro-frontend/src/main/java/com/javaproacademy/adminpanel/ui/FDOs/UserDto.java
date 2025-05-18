package com.javaproacademy.adminpanel.ui.FDOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
