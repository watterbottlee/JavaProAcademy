package com.javapro.javaproacademy.Backend.JwtSetup;

import java.util.Collection;
import java.util.Collections;

import com.javapro.javaproacademy.Backend.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public String getEmail(){
        return user.getEmail();
    }

    // Implement other methods required by UserDetails interface
    // For example:
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
