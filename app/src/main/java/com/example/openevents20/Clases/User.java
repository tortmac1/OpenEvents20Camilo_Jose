package com.example.openevents20.Clases;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private String accessToken;
    private Integer id;

    public User(String name, String last_name, String email, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.image = image;
    }

    public User(String name, String last_name, String email, String password, String image) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
