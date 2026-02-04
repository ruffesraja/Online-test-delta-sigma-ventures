package com.example.test.service;

import com.example.test.model.Movie;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovieService {
    private ArrayList<Movie> movies = new ArrayList<>();

    public Movie addMovie(String name, String description, int releaseYear) {
        Movie movie = new Movie(name, description, releaseYear);
        movies.add(movie);
        return movie;
    }

    public Optional<Movie> getMovieById(String id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }
}
