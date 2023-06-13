package com.suivius.models;


import com.suivius.repo.Issue;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private ProjectCategory category;

    @ManyToOne
    private Step step;

    @ManyToOne
    private StepStatus stepStatus;
    private String title;

    @OneToOne
    private Localisation localisation;

    @OneToOne
    private Authorization authorization;
    /**
     * prestataire
     */
    @ManyToOne
    private Vendor vendor;

    /**
     * user that created  the project
     */
    @ManyToOne
    private User creator;

    @ManyToOne
    private Tss tss;

    @OneToMany(mappedBy = "project")
    private Set<BOQ> boq;

    @OneToMany(mappedBy = "project")
    private Set<Issue> issues;


    @OneToMany(mappedBy = "project")
    private Set<ProjectWorkDetails> workDetails;

    /**
     * type de prestation
     */
    private String typeDelivery;

    private String typePartage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public StepStatus getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(StepStatus stepStatus) {
        this.stepStatus = stepStatus;
    }

    public String getTypeDelivery() {
        return typeDelivery;
    }

    public void setTypeDelivery(String type ) {
        this.typeDelivery = type ;
    }

    public String getTypePartage() {
        return typePartage;
    }

    public void setTypePartage(String type ) {
        this.typePartage = type ;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public Tss getTss() {
        return tss;
    }

    public void setTss(Tss tss) {
        this.tss = tss;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Set<BOQ> getBoq() {
        return boq;
    }

    public void setBoq(Set<BOQ> boq) {
        this.boq = boq;
    }

    public Set<ProjectWorkDetails> getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(Set<ProjectWorkDetails> workDetails) {
        this.workDetails = workDetails;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }
}
