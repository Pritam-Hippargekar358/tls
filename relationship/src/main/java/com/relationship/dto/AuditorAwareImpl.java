package com.relationship.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    private final String auditor = "System";
    @Override
    public Optional<String> getCurrentAuditor() {
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

//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider",
//        dateTimeProviderRef = "dateTimeProvider")
//public class AuditConfig {
//
//    @Bean
//    AuditorAware<String> auditorProvider() {
//// Implement logic to provide current auditor (user)
//        return new AuditorAwareImpl();
//    }
//
//    @Bean
//    public DateTimeProvider dateTimeProvider() {
//        // Implement logic to provide current date and time
//        return () -> Optional.of(LocalDateTime.now());
//    }
//
//}



        /*
        Some basic examples to get the logged-in user's id
        if you're using Spring Security.

        -- Example 1:
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername);

        -- Example 2:
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String auditor = "System";

        if (Objects.nonNull(authentication)) {
            auditor = authentication.getName();

            if (auditor.equals("anonymousUser")) {
                return Optional.of("System");
            }
        }

        return Optional.of(auditor);

         */