package com.relationship.runner;

import com.relationship.entity.Privilege;
import com.relationship.entity.Role;
import com.relationship.entity.User;
import com.relationship.repository.PrivilegeRepository;
import com.relationship.repository.RoleRepository;
import com.relationship.repository.UserRepository;
import com.relationship.service.CustomUserInfoService;
import com.relationship.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private CustomUserInfoService customUserInfoService;

    @Override
    public void run(String... args) throws Exception {

//        Privilege readPrivilege = new Privilege();
//        readPrivilege.setName("READ_PRIVILEGE");
//        Privilege writePrivilege = new Privilege();
//        writePrivilege.setName("WRITE_PRIVILEGE");
//        privilegeRepository.saveAll(Arrays.asList(readPrivilege, writePrivilege));
//
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        adminRole.setPrivileges(Arrays.asList(readPrivilege, writePrivilege));
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        userRole.setPrivileges(Arrays.asList(readPrivilege));
//        roleRepository.save(userRole);
//
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin123"));
//        admin.setRoles(Arrays.asList(adminRole));
//        userRepository.save(admin);
//
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("user123"));
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("admin", "admin123");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        final UserDetails userDetails = customUserInfoService.loadUserByUsername("admin");
        String idToken = jwtTokenService.generateToken(userDetails);
        logger.info("idToken:->{}", idToken);

    }
}
