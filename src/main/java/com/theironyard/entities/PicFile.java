package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by alhanger on 12/10/15.
 */
@Entity
@Table(name = "pics")
public class PicFile {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String originalName;

    @Column(nullable = false)
    public String name;


    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
