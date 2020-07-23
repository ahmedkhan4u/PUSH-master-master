package com.push.sweateliteathletics.softrasol.Models;

public class FavoritesModel {

    private String collection_name, document_name, name;

    public FavoritesModel() {
    }

    public FavoritesModel(String collection_name, String document_name, String name) {
        this.collection_name = collection_name;
        this.document_name = document_name;
        this.name = name;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
