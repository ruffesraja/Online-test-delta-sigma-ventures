package com.example.test.model;

import java.util.UUID;

public class Movie {
    private String id;
    private String name;
    private String description;
    private int releaseYear;

    public Movie(String name, String description, int releaseYear) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getReleaseYear() { return releaseYear; }
}
