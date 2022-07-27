package com.sec.demo.jwtsec.security;

import com.sec.demo.jwtsec.model.User;
import com.sec.demo.jwtsec.security.jwt.JwtUser;
import com.sec.demo.jwtsec.security.jwt.JwtUserFactory;
import com.sec.demo.jwtsec.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User with username"+ username +" not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("IN loadByUsername - user with username: succesfully loaded", username);

        return jwtUser;
    }
}
