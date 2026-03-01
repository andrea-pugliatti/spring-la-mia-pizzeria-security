package org.lessons.java.spring_la_mia_pizzeria_security.security;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_security.models.User;
import org.lessons.java.spring_la_mia_pizzeria_security.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userAttempt = userRepository.findByUsername(username);
        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("L'utente " + username + " non è stato trovato.");
        }
        return new DatabaseUserDetails(userAttempt.get());
    }

}
