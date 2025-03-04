package com.relationship.controller;

import com.relationship.dto.AuthResponse;
import com.relationship.dto.AuthRequest;
import com.relationship.service.CustomUserInfoService;
import com.relationship.service.JwtTokenService;
import com.relationship.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class SecureController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private CustomUserInfoService customUserInfoService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        try {
//            authenticationManager.authenticate(authenticationToken);
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }

//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
        final UserDetails userDetails = customUserInfoService.loadUserByUsername(login.getUsername());
        String idToken = jwtTokenService.generateToken(userDetails);
        return new ResponseEntity<>(new AuthResponse(idToken), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("adminOnlyMethod")
    public ResponseEntity<?> adminOnlyMethod() {
        return new ResponseEntity<>( ResponseUtils.createSuccessResponse("Admin only method"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("userAndAdminMethod")
    public ResponseEntity<?> userAndAdminMethod() {
        return new ResponseEntity<>( ResponseUtils.createSuccessResponse("User and Admin method"), HttpStatus.OK);
    }
}
