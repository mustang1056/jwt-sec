package com.sec.demo.jwtsec.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private JwTokenProvider jwTokenProvider;

    public JwtTokenFilter(JwTokenProvider jwTokenProvider) {
        this.jwTokenProvider = jwTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        if(token!=null && jwTokenProvider.validateToken(token)){
            Authentication authentication = jwTokenProvider.getAuthentication(token);
            if(authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
