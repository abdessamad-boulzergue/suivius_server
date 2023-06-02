package com.suivius.models;

import javax.persistence.*;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
