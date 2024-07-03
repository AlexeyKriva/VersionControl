package org.app.versioncontrol.services;

import org.app.versioncontrol.entities.project.Project;
import org.app.versioncontrol.entities.project.ProjectPatchDTO;
import org.app.versioncontrol.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project, long userId) {
        project.setAddedDate(LocalDateTime.now());
        project.setUserId(userId);
        projectRepository.save(project);

        return project;
    }

    public List<Project> selectProjects(long userId) {
        return projectRepository.findAllByUserId(userId).orElse(new ArrayList<>());
    }

    public Project updateProject(ProjectPatchDTO projectPatchDTO, long projectId) {
        Optional<Project> projectFromDb = projectRepository.findProjectById(projectId);
        if (projectFromDb.isPresent()) {
            Project project = projectFromDb.get();
            project.setName(projectPatchDTO.getName());
            project.setDescription(projectPatchDTO.getDescription());
            projectRepository.save(project);

            return project;
        }

        return null;
    }

    public void deleteProject(long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
        }
    }
}