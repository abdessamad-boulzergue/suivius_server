package com.suivius.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private StaffMember member;

    @ManyToOne
    private Project project;

    private LocalTime normalHours;

    private LocalTime additionalHours;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffMember getMember() {
        return member;
    }

    public void setMember(StaffMember member) {
        this.member = member;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalTime getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(LocalTime normalHours) {
        this.normalHours = normalHours;
    }

    public LocalTime getAdditionalHours() {
        return additionalHours;
    }

    public void setAdditionalHours(LocalTime additionalHours) {
        this.additionalHours = additionalHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
