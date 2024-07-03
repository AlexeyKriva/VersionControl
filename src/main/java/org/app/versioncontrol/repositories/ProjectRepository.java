package org.app.versioncontrol.repositories;

import org.app.versioncontrol.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectById(long id);
    Optional<Project> findProjectByUserId(long userId);
    Optional<List<Project>> findAllByUserId(long userId);
}