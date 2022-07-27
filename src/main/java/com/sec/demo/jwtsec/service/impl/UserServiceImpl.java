package com.sec.demo.jwtsec.service.impl;

import com.sec.demo.jwtsec.model.Role;
import com.sec.demo.jwtsec.model.Status;
import com.sec.demo.jwtsec.model.User;
import com.sec.demo.jwtsec.repository.RoleRepository;
import com.sec.demo.jwtsec.repository.UserRepository;
import com.sec.demo.jwtsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User userRegister(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
