package com.example.Assignment.service;

import com.example.Assignment.model.MovieRatingDto;
import com.example.Assignment.model.entity.Movies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    Movies getMovieById(String id);
    List<Movies> findLast10OrderByRuntimeMinutesDesc();
    String saveNewMovie(Movies movies);

    List<MovieRatingDto> getTopRatedMovies();
}
