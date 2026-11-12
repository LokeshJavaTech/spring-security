package com.lokesh.springsecurity.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("EAZYBANK-ERROR-REASON-HEADER", "Authentication failed");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String jsonResponse = String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                                            LocalDateTime.now(),
                                            HttpStatus.UNAUTHORIZED.value(),
                                            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                                            (authException != null && authException.getMessage() != null) ? authException.getMessage() : "Unauthorized",
                                            request.getRequestURI());
        response.getWriter().write(jsonResponse);
    }
}
