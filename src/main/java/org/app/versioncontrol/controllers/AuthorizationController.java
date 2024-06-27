package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        System.out.println(LocalDateTime.now());
        return "/authorization/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/authorization/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/authorization/registration";
        }

        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            return "/authorization/registration";
        }
        if (userService.saveUser(user) == null) {
            return "/authorization/registration";
        }

        return "redirect: /authorization/login";
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("qwerty");
        return "/authorization/admin-login";
    }
}
