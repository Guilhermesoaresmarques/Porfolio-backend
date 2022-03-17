package com.guilherme.portfolio.service;

import com.guilherme.portfolio.model.Role;
import com.guilherme.portfolio.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
