package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.authorization.User;
import org.app.versioncontrol.entities.project.Project;
import org.app.versioncontrol.services.ProjectService;
import org.app.versioncontrol.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @PostMapping("/add-project")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Project> addProject(@ModelAttribute("project") Project project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        Project savedProject = projectService.saveProject(project, user.getId());

        return ResponseEntity.ok(savedProject);
    }
}
