package com.angelarreola.theweeknd_api.config;

import com.angelarreola.theweeknd_api.entities.Role;
import com.angelarreola.theweeknd_api.entities.User;
import com.angelarreola.theweeknd_api.repositories.RoleRepository;
import com.angelarreola.theweeknd_api.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {

        /* Create roles if they don't exist */
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role role = new Role();
            role.setName("ADMIN");
            role.setDescription("Administrator role");
            return roleRepository.save(role);
        });

        Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
            Role role = new Role();
            role.setName("USER");
            role.setDescription("Regular user role");
            return roleRepository.save(role);
        });

        Role assistantRole = roleRepository.findByName("ASSISTANT").orElseGet(() -> {
           Role role = new Role();
           role.setName("ASSISTANT");
           role.setDescription("Assistant role");
           return roleRepository.save(role);
        });

        // Create users if there is no one.
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user1").isEmpty()) {
            User user1 = new User();
            user1.setFirstName("User");
            user1.setLastName("One");
            user1.setUsername("user1");
            user1.setEmail("user1@example.com");
            user1.setPassword(passwordEncoder.encode("user123"));
            user1.setRoles(Set.of(userRole));
            userRepository.save(user1);
        }

        if (userRepository.findByUsername("user2").isEmpty()) {
            User user2 = new User();
            user2.setFirstName("User2");
            user2.setLastName("Two");
            user2.setUsername("user2");
            user2.setEmail("user2@example.com");
            user2.setPassword(passwordEncoder.encode("user123"));
            user2.setRoles(Set.of(userRole));
            userRepository.save(user2);
        }

        if (userRepository.findByUsername("assistant").isEmpty()) {
            User assistant = new User();
            assistant.setFirstName("Assistant");
            assistant.setLastName("Helper");
            assistant.setUsername("assistant");
            assistant.setEmail("assistant@example.com");
            assistant.setPassword(passwordEncoder.encode("assistant123"));
            assistant.setRoles(Set.of(assistantRole));
            userRepository.save(assistant);
        }

        System.out.println("✅ Initial data loaded successfully");
    }

}
