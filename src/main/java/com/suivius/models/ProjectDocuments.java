package com.suivius.models;

import javax.persistence.*;

@Entity
public class ProjectDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Document document;

    private ProjectDocumentType type;

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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public ProjectDocumentType getType() {
        return type;
    }

    public void setType(ProjectDocumentType type) {
        this.type = type;
    }
}
