package com.app.backend.repository;

import com.app.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>finByUsername(String username);
    boolean existByUsername(String username);
    boolean existsByEmail(String email);
}
