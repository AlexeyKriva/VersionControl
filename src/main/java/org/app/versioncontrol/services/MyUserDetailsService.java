package org.app.versioncontrol.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.entities.authorization.dto.MyUserDetails;
import org.app.versioncontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(MyUserDetails::new).orElse(null);
    }

    public boolean saveUser(User user) {
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb.isPresent()) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return true;
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

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
