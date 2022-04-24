package com.example.emtlab2.service;

import com.example.emtlab2.model.User;
import com.example.emtlab2.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String s);
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
