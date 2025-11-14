
package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.servce.UserService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','COORDINADOR')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
}

@PostMapping
    @PreAuthorize("hasRole('ADMIN','COORDINADOR')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
}

@PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','COORDINADOR')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try{
         return ResponseEntity.ok(userService.update(id, user));
        } 
        } catch (RunTimeException e) {
            if( e.getMessage().contains("User not found")) {
                return ResponseEntity.status(403).body(new MessageResponse(e.getMessage()));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));

        }


@DeleteMapping(value ="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok(new MessageResponse("Usuario eliminado exitosamente"));

        }catch (RunTimeException e) {
            returnResponseEbtyty.badResquest().body( new MessageResponse(e.getMessage()));
        }
    }
}

