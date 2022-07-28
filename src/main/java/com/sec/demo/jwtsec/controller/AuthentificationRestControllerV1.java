package com.sec.demo.jwtsec.controller;

import com.sec.demo.jwtsec.dto.AuthentificationRequestDTO;
import com.sec.demo.jwtsec.model.User;
import com.sec.demo.jwtsec.security.jwt.JwTokenProvider;
import com.sec.demo.jwtsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthentificationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwTokenProvider jwTokenProvider;
    private final UserService userService;
    @Autowired
    public AuthentificationRestControllerV1(AuthenticationManager authenticationManager, JwTokenProvider jwTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwTokenProvider = jwTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthentificationRequestDTO requestDTO){
        try{
            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }
            String token = jwTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }


}
