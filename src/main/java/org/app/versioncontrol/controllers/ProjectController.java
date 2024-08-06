package org.app.versioncontrol.controllers;

import org.app.versioncontrol.entities.project.Project;
import org.app.versioncontrol.entities.project.ProjectPatchDTO;
import org.app.versioncontrol.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vercontr/user")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{userId}/add-project")
    public ResponseEntity<Project> addProject(@RequestBody Project project, @PathVariable("userId") long userId) {
        Project savedProject = projectService.saveProject(project, userId);
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping("/{userId}/see-projects")
    public ResponseEntity<List<Project>> seeProjects(@PathVariable("userId") long userId) {
        List<Project> projects = projectService.selectProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @PatchMapping("/{projectId}/update-project")
    public ResponseEntity<Project> updateProject(@RequestBody ProjectPatchDTO projectPatchDTO,
                                                 @PathVariable("projectId") long projectId) {
        Project project = projectService.updateProject(projectPatchDTO, projectId);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}/delete-project")
    public ResponseEntity<Project> deleteProject(@PathVariable("projectId") long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
