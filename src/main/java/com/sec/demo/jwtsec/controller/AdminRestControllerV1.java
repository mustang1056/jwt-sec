package com.sec.demo.jwtsec.controller;

import com.sec.demo.jwtsec.dto.AdminUserDTO;
import com.sec.demo.jwtsec.dto.UserDTO;
import com.sec.demo.jwtsec.model.User;
import com.sec.demo.jwtsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminRestControllerV1 {


    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="user/{id}")
    public ResponseEntity<AdminUserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        AdminUserDTO adminUserDTO = AdminUserDTO.fromUser(user);

        return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
    }


}
