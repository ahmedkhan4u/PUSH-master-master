package com.push.sweateliteathletics.softrasol.Models;

public class ProgramsModel {

    private String title, sub_title;
    int image;

    public ProgramsModel() {
    }

    public ProgramsModel(String title, String sub_title, int image) {
        this.title = title;
        this.sub_title = sub_title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
