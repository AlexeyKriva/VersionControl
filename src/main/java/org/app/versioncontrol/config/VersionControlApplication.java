package org.app.versioncontrol.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.app")
public class VersionControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionControlApplication.class, args);
    }

}
