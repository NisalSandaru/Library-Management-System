package com.nisal.Library.Management.System.service.impl;

import com.nisal.Library.Management.System.domain.UserRole;
import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        initializeAdminUser();
    }

    private void initializeAdminUser(){
        String adminEmail="nisalsandaru1@gmail.com";
        String adminPassword="123456";

        if (userRepository.findByEmail(adminEmail)==null){
            User user=User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName("Nisal Sandaru")
                    .role(UserRole.ROLE_ADMIN)
                    .build();

            User admin = userRepository.save(user);
        }
    }
}
