package com.suivius.models;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class ToolsUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private WorkTool tool;

    @ManyToOne
    private Project project;

    private LocalTime timeUsage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkTool getTool() {
        return tool;
    }

    public void setTool(WorkTool tool) {
        this.tool = tool;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalTime getTimeUsage() {
        return timeUsage;
    }

    public void setTimeUsage(LocalTime timeUsage) {
        this.timeUsage = timeUsage;
    }
}
