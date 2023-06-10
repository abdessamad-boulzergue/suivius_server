package com.suivius.models;

public enum ProjectDocumentType {

    CROQUIS("CROQUIS"),
    APD("APD"),
    TSS_IMAGE("TSS_IMAGE"),
    UNKOWN("UNKOWN");

    private final String type;
    ProjectDocumentType(String type){
        this.type =type;
    }

}
