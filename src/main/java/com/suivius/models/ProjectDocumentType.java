package com.suivius.models;

public enum ProjectDocumentType {

    CROQUIS("CROQUIS"),
    APD("APD");

    private final String type;
    ProjectDocumentType(String type){
        this.type =type;
    }

}
