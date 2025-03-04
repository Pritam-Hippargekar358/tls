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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
    //  This handler is triggered when an authenticated user tries to access a resource without the necessary permissions
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        logger.error("Request Uri : {}", request.getRequestURI());
//        logger.error("No Authorities", accessDeniedException);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> errorResponse = ResponseUtils.createErrorResponse("You do not have permission to access this resource.", Map.of("type", "FORBIDDEN"));
        ObjectMapper mapper = new ObjectMapper();


        mapper.writeValue(response.getWriter(), errorResponse);
    }
}
