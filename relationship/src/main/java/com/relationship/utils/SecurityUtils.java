package com.relationship.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class SecurityUtils {
    public static final String auditor = "System";

    public static Optional<String> getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getName().equals("anonymousUser")) {
//            return Optional.of("System");
//        }
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            return Optional.of(auditor);
        }
        return Optional.ofNullable(authentication.getName());
    }
}
//CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//String authorities = customUserDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//claims.get(EMAIL_KEY, String.class)
//List<String> roles = new ArrayList<>(Arrays.asList(claims.get(AUTHORITIES_KEY, String.class).split(",")));
//List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());


//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//UserDetails userDetails = (UserDetails)principal;
//String username = principal.getUsername();
//String password = principal.getPassword();