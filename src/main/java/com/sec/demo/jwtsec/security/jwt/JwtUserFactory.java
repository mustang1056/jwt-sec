package com.sec.demo.jwtsec.security.jwt;

import com.sec.demo.jwtsec.model.Role;
import com.sec.demo.jwtsec.model.Status;
import com.sec.demo.jwtsec.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory(){

    }

    public static JwtUser create(User user){
        return new JwtUser(
               user.getId(),
               user.getUsername(),
               user.getFirstname(),
               user.getLastname(),
               user.getEmail(),
               user.getPassword(),
               mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
               user.getStatus().equals(Status.ACTIVE),
               user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
