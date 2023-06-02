package com.suivius.models;

import javax.persistence.*;

@Entity
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double lat;

    private double lng;

    private String region;

    private String province;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
