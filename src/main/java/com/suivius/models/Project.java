package com.suivius.models;


import javax.persistence.*;

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
}
