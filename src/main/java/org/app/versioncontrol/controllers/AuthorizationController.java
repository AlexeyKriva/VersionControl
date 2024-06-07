package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    @Autowired
    private MyUserDetailsService userService;

    @GetMapping("/login")
    public String login() {
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
        System.out.println(user);
        if (!userService.saveUser(user)) {
            return "/authorization/registration";
        }
        System.out.println("ТУТ");

        return "redirect: /authorization/login";
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("qwerty");
        return "/authorization/admin-login";
    }
}
