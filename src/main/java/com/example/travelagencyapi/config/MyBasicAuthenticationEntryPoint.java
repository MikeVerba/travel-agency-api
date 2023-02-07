package com.example.travelagencyapi.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.println("HTTP Status 401 - " + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MikeVerba");
        super.afterPropertiesSet();
    }
}
