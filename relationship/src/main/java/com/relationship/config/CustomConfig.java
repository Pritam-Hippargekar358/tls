package com.relationship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//@EnableJpaRepositories("com.relationship.repository")
public class CustomConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new com.relationship.config.AuditorAwareImpl();
    }

}


//Accessing the security context to retrieve relevant information for current request.
//SecurityContext securityContext = SecurityContextHolder.getContext();
//Authentication authentication = securityContext.getAuthentication();
//Object principal = authentication.getPrincipal();
//String username = authentication.getName();
//Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

// Setting spring security context holder strategy
//@PostConstruct
//public void setSecurityContextHolderStrategy() {
//    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//}