//package org.app.versioncontrol.entities.project;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "projects")
//public class Project {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    @Column(name = "name")
//    private String projectName;
//    @Column(name = "added_date")
//    private Date addedDate;
//    @Column(name = "project_access")
//    private ProjectAccess projectAccess;
//    @Column(name = "user_id")
//    private long userId;
//}
