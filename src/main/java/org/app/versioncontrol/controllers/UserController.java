package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.entities.authorization.UserAddDto;
import org.app.versioncontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verscontr/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody UserAddDto userAddDto) {
        User newUser = userService.saveUser(userAddDto);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{userId}/delete-user/")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/change-password")
    public ResponseEntity<Boolean> changePassword(@PathVariable("userId") long userId, String password,
                                               String confirmedPassword) {
        Boolean result = userService.changePassword(userId, password, confirmedPassword);
        return ResponseEntity.ok(result);
    }
}
