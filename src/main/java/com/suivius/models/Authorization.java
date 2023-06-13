package com.suivius.models;

import com.suivius.models.Project;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dateDemand;
    private LocalDate dateCommission;
    private LocalDate dateDecision;
    private LocalDate datePayment;
    private LocalDate dateSign;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDemand() {
        return dateDemand;
    }

    public void setDateDemand(LocalDate dateDemand) {
        this.dateDemand = dateDemand;
    }

    public LocalDate getDateCommission() {
        return dateCommission;
    }

    public void setDateCommission(LocalDate dateCommission) {
        this.dateCommission = dateCommission;
    }

    public LocalDate getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(LocalDate dateDecision) {
        this.dateDecision = dateDecision;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }

    public LocalDate getDateSign() {
        return dateSign;
    }

    public void setDateSign(LocalDate dateSign) {
        this.dateSign = dateSign;
    }
}
