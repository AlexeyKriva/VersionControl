package org.app.versioncontrol.services;

import lombok.extern.slf4j.Slf4j;
import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new User();
        }
        return user.orElseGet(User::new);
    }

    public User saveUser(User user) {
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb.isPresent()) {
            return new User();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        return user;
    }

    public boolean deleteUser(long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }

    public User findByUserId(long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
