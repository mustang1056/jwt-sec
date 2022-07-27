package com.sec.demo.jwtsec.security.jwt;

import com.sec.demo.jwtsec.model.Role;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwTokenProvider {

    public String createToken(String username, List<Role> role){

    }

    public Authentication getAuthentication(String token){

    }

    public String getUsername(String token){

    }

    public boolean booleanvalidateToken(){

    }

    private List<String> getRoleNames(List<Role> ){

    }
}
