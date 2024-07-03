package org.app.versioncontrol.entities.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "added_date")
    private LocalDateTime addedDate;
    @Column(name = "with_access")
    @Enumerated(EnumType.STRING)
    private ProjectAccess withAccess;
}
