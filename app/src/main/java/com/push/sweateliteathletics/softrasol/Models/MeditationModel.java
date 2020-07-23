package com.push.sweateliteathletics.softrasol.Models;

public class MeditationModel {


    private String audio_url, category, description, image_url, title;

    public MeditationModel() {
    }

    public MeditationModel(String audio_url, String category, String description, String image_url, String title) {
        this.audio_url = audio_url;
        this.category = category;
        this.description = description;
        this.image_url = image_url;
        this.title = title;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
