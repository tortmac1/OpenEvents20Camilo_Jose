package com.example.openevents20;

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
