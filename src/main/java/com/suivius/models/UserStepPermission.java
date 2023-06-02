package com.suivius.models;

public enum UserStepPermission {

    DEMARRER_ETUDE("DEMARRER_ETUDE"),
    EDITER_TSS("EDITER_TSS"),
    PRE_VALIDATION_TSS("PRE_VALIDATION_TSS"),
    VALIDATION_TSS("VALIDATION_TSS"),
    EDITER_ADP("EDITER_ADP"),
    PRE_VALIDATION_APD("PRE_VALIDATION_APD"),
    VALIDATION_APD("VALIDATION_APD");

    private String value;
    UserStepPermission(String value) {
        this.value= value;
    }
}
