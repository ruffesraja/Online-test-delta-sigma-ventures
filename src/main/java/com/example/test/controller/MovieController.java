package com.example.test.controller;

import com.example.test.model.Movie;
import com.example.test.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestParam(required = false) String name, 
                                          @RequestParam(required = false) String description, 
                                          @RequestParam(required = false) Integer year) {
        StringBuilder errors = new StringBuilder();
        
        if (name == null || name.isEmpty()) {
            errors.append("Missing required field: name. ");
        }
        if (description == null || description.isEmpty()) {
            errors.append("Missing required field: description. ");
        }
        if (year == null) {
            errors.append("Missing required field: year. ");
        } else if (year < 1888 || year > 2100) {
            errors.append("Invalid year: must be between 1888 and 2100. ");
        }
        
        if (errors.length() > 0) {
            return ResponseEntity.badRequest().body(errors.toString().trim());
        }
        
        Movie movie = movieService.addMovie(name, description, year);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added with ID: " + movie.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable String id) {
        return movieService.getMovieById(id)
                .<ResponseEntity<?>>map(movie -> ResponseEntity.ok(movie))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found"));
    }
}
