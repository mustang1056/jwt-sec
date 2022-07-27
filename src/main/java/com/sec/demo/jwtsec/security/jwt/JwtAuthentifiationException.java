package com.sec.demo.jwtsec.security.jwt;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthentifiationException extends AuthenticationException {

    public JwtAuthentifiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthentifiationException(String msg) {
        super(msg);
    }
}
