package com.suivius.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Project project;

    private @ManyToOne
    User user;

    private LocalDateTime affectationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
