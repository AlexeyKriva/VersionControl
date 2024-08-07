package org.app.versioncontrol.services;

import lombok.extern.slf4j.Slf4j;
import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.entities.authorization.UserAddDto;
import org.app.versioncontrol.entities.authorization.UserMapper;
import org.app.versioncontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService{
    @Autowired
    private UserRepository userRepository;
    private static final UserMapper USER_MAPPER = UserMapper.INSTANSE;


    public User saveUser(UserAddDto userAddDto) {
        Optional<User> userFromDb = userRepository.findByUsername(userAddDto.getUsername());
        if (userFromDb.isPresent()) {
            return new User();
        } else if (doPasswordsMatch(userAddDto.getPassword(), userAddDto.getConfirmedPassword())) {
            User newUser = USER_MAPPER.fromUserAddDtoToUser(userAddDto);
            newUser.setEnabled(true);

            userRepository.save(newUser);
        }

        return new User();
    }

    public boolean doPasswordsMatch(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    public boolean deleteUser(long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }

    public boolean changePassword(long userId, String password, String confirmedPassword) {
        if (doPasswordsMatch(password, confirmedPassword)) {
            Optional<User> userFromDb = findByUserId(userId);
            if (userFromDb.isPresent()) {
                User updatingUser = userFromDb.get();
                updatingUser.setPassword(password);
                userRepository.save(updatingUser);
            }
        }
        return false;
    }

    public Optional<User> findByUserId(long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
