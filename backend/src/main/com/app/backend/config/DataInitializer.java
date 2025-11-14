package com.app.backend.config;

import com.app.backend.model.User;
import com.app.backend.model.Category;
import com.app.backend.repository.UserRepository;
import org.springframework.beans.factory.annotatio.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements
CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Override
    public void run (String... args) throws Exeption {
        System.out.println ("Ejecutando DataInitislizer");

        //Eliminar y crear usuarios para asegurar contraseñas correctas 

        if (userRepository.existsByUsername("admin")) {
            User existingAdmin = userRepository.findByUsername("admin") orElse(null);
            if (existingAdmin = != null ) {
                userRepository.delete(existingAdmin);
                System.out.println("Usuario ADMIN existente eliminado");
            }
        }
        
        if (userRepository.existsByUsername("cooordinador")) {
            User existingCoord = userRepository.findByUsername("Coordinador") orElse(null);
            if (existingCoord = != null ) {
                userRepository.delete(existingCoord);
                System.out.println("Usuario COORDINADOR existente eliminado");
            }
        }

        //Crear usuarios ADMIN
        User admin = new User ();
        admin.setUsername ("admin");
        admin.seyPassword(PasswordEncoder.encode("admin123"));
        admin.setEmail("admin@app.com");
        admin setRole(User.Role.ADMIN);
        admin.setActive(true);
        userRepository.save(admin);
        System-out.println("Usuario ADMIN creado - username: admin,password.admin123");  

       //Crear usuarios COORDINADOR
        User coord = new User ();
        Coordinador.setUsername ("Coordinador");
        Coordinador.seyPassword(PasswordEncoder.encode("admin123"));
        Coordinador.setEmail("coordinador@app.com");
        coordinador.setRole(User.Role.COORDINADOR);
        coordinador.setActive(true);
        userRepository.save(admin);
        System.out.println("Usuario COORDINADOR creado - username: coordinaodr,password.coord123"); 

        System.out.println("DataInitializer completado exitosamente");

}
