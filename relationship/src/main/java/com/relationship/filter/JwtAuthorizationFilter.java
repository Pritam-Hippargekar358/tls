package com.relationship.filter;

import com.relationship.service.CustomUserInfoService;
import com.relationship.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import java.io.IOException;
import java.util.Objects;

//https://www.ignek.com/blog/spring-security-jwt-authentication-authorization/
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private static final String BEARER_PREFIX = "Bearer ";
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private CustomUserInfoService customUserInfoService;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = parseJwt(request);
            if(StringUtils.isBlank(jwtToken)){
                logger.info("Executing JwtRequestFilter jwtToken is null.");
                filterChain.doFilter(request, response); // Proceeding with the filter chain
                return;
            }

            String username = this.jwtTokenService.extractUsername(jwtToken);
            logger.info("Extracting username from jwtToken: {}",username);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (StringUtils.isNotBlank(username) && Objects.isNull(authentication)) {
                UserDetails userDetails = this.customUserInfoService.loadUserByUsername(username);
                if (this.jwtTokenService.isValidAccessToken(jwtToken, userDetails)) {
                    //SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //context.setAuthentication(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    //SecurityContextHolder.setContext(context);
                }
            }
            filterChain.doFilter(request, response); // Proceeding with the filter chain
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

//        if (request.getServletPath().contains("/api/v1/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

//        // JWT validation is Skipped for these public endpoints
//        String path = request.getRequestURI();
//        if (path.startsWith("/api/auth/login/**") || path.startsWith("/api/auth/users") || path.startsWith("/api/auth/access-token")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

//            Boolean isTokenExpired = this.jwtTokenService.isTokenExpired(jwtToken);
//            if (isTokenExpired) {
//                Map<String, Object> errorResponse = ResponseUtils.createErrorResponse("Token already expired.");
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.writeValue(response.getWriter(), errorResponse);
//                return;
//            }

    }

    private String parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (org.springframework.util.StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(7); //authHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}

//            if (jwtService.isTokenValid(jwt, userDetails)) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                userDetails,
//                null,
//                userDetails.getAuthorities()
//        );
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        context.setAuthentication(authToken);
//                SecurityContextHolder.setContext(context);
//            }