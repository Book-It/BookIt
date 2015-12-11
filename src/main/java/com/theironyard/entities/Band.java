package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by alhanger on 12/8/15.
 */
@Entity
@Table (name = "bands")
public class Band {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String location;

    @Column(nullable = false)
    public String genre;

    @ManyToOne
    public User user;

    @OneToOne
    public PicFile pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
