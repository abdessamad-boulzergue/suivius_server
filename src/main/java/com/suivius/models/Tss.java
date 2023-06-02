package com.suivius.models;

import javax.persistence.*;

@Entity
public class Tss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    ConnectionType connectionType;

    @ManyToOne
    CableType cableType;

    @ManyToOne
    EquipmentType equipmentType;

    @ManyToOne
    SiteType siteType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public CableType getCableType() {
        return cableType;
    }

    public void setCableType(CableType cableType) {
        this.cableType = cableType;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public void setSiteType(SiteType siteType) {
        this.siteType = siteType;
    }
}
