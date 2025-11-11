package com.app.backend.security;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;

  import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired 
    private UserRepository userRepository;

    @Override
    public UserDetails loaUserByUsername(String username) throws 
    UsernameNotFoundException {
        org.apache.catalina.User user = userRepository.findByUsername(username);
        .orElseThrow(() -> new UsernameNotFoundException("User no encontrado" + username));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getActive(),
            accountNotExpired: true, credentialsNonExpired: true, acc... true, getAuthorities(user));


    }

    private Collection <? extends GrantedAuthority> getAthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

}