package com.didapteam.project.education;

import java.io.Serializable;

public class Place implements Serializable {
    private  String name, description;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    private int photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String from) {
        this.description = from;
    }

}
