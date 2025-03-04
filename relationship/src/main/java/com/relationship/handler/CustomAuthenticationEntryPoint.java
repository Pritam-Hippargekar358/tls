package com.relationship.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relationship.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
    //  This handler is triggered when an unauthenticated request tries to access a protected resource.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        logger.error("Request Uri : {}", request.getRequestURI());
//        logger.error("Not Authenticated Request", authException);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> errorResponse = ResponseUtils.createErrorResponse("Authentication is required to access this resource.", Map.of("type", "UNAUTHORIZED"));
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getWriter(), errorResponse);
    }
}
