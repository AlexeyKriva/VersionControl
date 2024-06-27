package org.app.versioncontrol.services;

import org.app.versioncontrol.entities.project.Project;
import org.app.versioncontrol.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project, long userId) {
        project.setAddedDate(new Date());
        project.setUserId(userId);
        projectRepository.save(project);

        return project;
    }
}