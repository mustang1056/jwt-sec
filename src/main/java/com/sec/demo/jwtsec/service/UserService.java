package com.sec.demo.jwtsec.service;

import com.sec.demo.jwtsec.model.User;

import java.util.List;

public interface UserService {
    User userRegister(User user);
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);
    void delete(Long id);
}
