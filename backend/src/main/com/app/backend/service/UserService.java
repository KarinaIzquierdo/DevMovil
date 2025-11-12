package com.app.backend.service;

import com.app.backend.dto.UserCreateResquest;
import com.app.backend.dto.UserUpdateRequest;
import com.app.backend.model.User;
import com.app.backend.repository.UserRespository;
import org.springframework.beans.factory.annotatio.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.estereotype.Service;
import java.util.List;

@Service 
public class UserService(){
@Autowired
private UserRespository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

public List<User> findAll() {
    return userRepository.findAll();
}

public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RunTimeException("Usuario no encontrado"));
}

public User create(UserCreatedRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword())); 
    user.setEmail(request.getEmail());
    user.setRole (request.getRole());
    user.setActive(request.getActive() != null ? request.getActive() : true);
    return userRepository.save(user);
} 

public User update(Long id, UserUpdateRequest request) {
    User user = findById(id);
    
    //validar que el coord no pueda modificar el admin principal
    if(id == 1L && isCoordinador()) {
        throw new RunTimeException("no tienes permiso para modificar el admin principal .l.");

    }

    user.setUserName(request.getUsername());
    user.setEmail(request.getEmail());
    user.setRole(request.getRole());
    user.setActive(request.getActive());

    if(request.getPassword() != null && !request.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    return userRepository.save(user);
    }

    public Boolean isCoordinador() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities() != null) {
            return auth.getAuthorities().stream().anyMatch(authen -> authen.getAuthority().equals("ROLE_COORDINADOR"));
        }

        return false;
    }

    public void delete(Long id) {
        User user = findById(id);

        //validar que no se elimine el usuario admin principal

        if (id == 1L) {
            throw new RunTimeException("No se puede eliminar el admin principal");
        }

        //Validar que el user exista

        if(user ==null) {
            throw new RunTimeException("Usuario no encontrado");
        }

        userRepository.delete(user);
    }
}