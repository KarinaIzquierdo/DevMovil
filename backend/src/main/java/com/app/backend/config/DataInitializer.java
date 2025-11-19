package com.app.backend.config;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando DataInitializer");

        // Eliminar y crear usuarios para asegurar contraseñas correctas 

        if (userRepository.existsByUsername("admin")) {
            Optional<User> existingAdmin = userRepository.findByUsername("admin");
            if (existingAdmin.isPresent()) {
                userRepository.delete(existingAdmin.get());
                System.out.println("Usuario ADMIN existente eliminado");
            }
        }
        
        if (userRepository.existsByUsername("coordinador")) {
            Optional<User> existingCoord = userRepository.findByUsername("coordinador");
            if (existingCoord.isPresent()) {
                userRepository.delete(existingCoord.get());
                System.out.println("Usuario COORDINADOR existente eliminado");
            }
        }

        // Crear usuario ADMIN
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@app.com");
        admin.setRole(User.Role.ADMIN);
        admin.setActive(true);
        userRepository.save(admin);
        System.out.println("Usuario ADMIN creado - username: admin, password: admin123");

        // Crear usuario COORDINADOR
        User coordinador = new User();
        coordinador.setUsername("coordinador");
        coordinador.setPassword(passwordEncoder.encode("coord123"));
        coordinador.setEmail("coordinador@app.com");
        coordinador.setRole(User.Role.COORDINADOR);
        coordinador.setActive(true);
        userRepository.save(coordinador);
        System.out.println("Usuario COORDINADOR creado - username: coordinador, password: coord123");

        System.out.println("DataInitializer completado exitosamente");
    }
}
