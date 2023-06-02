package com.suivius.models;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Unite unite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
