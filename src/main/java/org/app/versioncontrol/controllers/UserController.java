package org.app.versioncontrol.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{username}/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String profile() {
        return "/user/profile";
    }
}
