package com.example.openevents20.Clases;

import java.io.Serializable;

public class Event implements Serializable {
    private Integer id;
    private String name;
    private String image;
    private String location;
    private String description;
    private String eventStart_date;
    private String eventEnd_date;
    private Integer n_participators;
    private String type;
    private Integer owner_id;
    private String date;


    public Event( String name, String image, String location, String description, Integer n_participators, String type) {

        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.n_participators = n_participators;
        this.type = type;
    }

    public Event(String name, String image, String location, String description, String eventStart_date, String eventEnd_date, Integer n_participators, String type) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.n_participators = n_participators;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getEventStart_date() {
        return eventStart_date;
    }

    public String getEventEnd_date() {
        return eventEnd_date;
    }

    public Integer getN_participators() {
        return n_participators;
    }

    public String getType() {
        return type;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public String getDate() {
        return date;
    }
}
