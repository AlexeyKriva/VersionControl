package org.app.versioncontrol.entities.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPatchDTO {
    private String name;
    private String description;
}
