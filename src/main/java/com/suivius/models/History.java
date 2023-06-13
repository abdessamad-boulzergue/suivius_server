package com.suivius.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Project project;

    @ManyToOne
    private StepStatus previousStatus;

    @ManyToOne
    private StepStatus currentStatus;

    private LocalDateTime dateTime;

    @OneToOne
    private Localisation localisation;

    private String deviceId;


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

    public StepStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(StepStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    public StepStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StepStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
