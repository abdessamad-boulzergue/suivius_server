package com.suivius.models;

import javax.persistence.*;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    Project project;

    @ManyToOne
    User user;

    @ManyToOne
    Step step;

    UserStepPermission permission;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public UserStepPermission getPermission() {
        return permission;
    }

    public void setPermission(UserStepPermission permission) {
        this.permission = permission;
    }
}
