package com.example.Assignment.controller;


import com.example.Assignment.model.MovieDto;
import com.example.Assignment.model.MovieRatingDto;
import com.example.Assignment.model.entity.Movies;
import com.example.Assignment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/health")
    public String checkHealth(){
        return "UP";
    }

    @GetMapping("/movie/{id}")
    public Movies getMovie(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
    @GetMapping("/longest-duration-movies")
    public List<Movies> getLongestDurationMovies(){
        return movieService.findLast10OrderByRuntimeMinutesDesc();
    }

    @PostMapping("/new-movie")
    public String saveNewMovie(@RequestBody Movies movies){
        return movieService.saveNewMovie(movies);
    }

    @GetMapping("/top-rated-movies")
    public List<MovieRatingDto> getTopRatedMovies(){
        return movieService.getTopRatedMovies();
    }


}
