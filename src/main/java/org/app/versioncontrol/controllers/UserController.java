package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.entities.project.Project;
import org.app.versioncontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.loadUserByUsername(authentication.getName());

        return "/user/profile";
    }

    @GetMapping("/add-project")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());

        return "/user/add-project";
    }
}