package org.app.versioncontrol.entities.authorization;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;
}
