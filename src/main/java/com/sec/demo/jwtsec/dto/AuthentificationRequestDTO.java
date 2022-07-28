package com.sec.demo.jwtsec.dto;

import lombok.Data;

@Data
public class AuthentificationRequestDTO {
    private String username;
    private String password;
}
