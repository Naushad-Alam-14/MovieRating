package com.example.Assignment.service.impl;

import com.example.Assignment.model.MovieRatingDto;
import com.example.Assignment.model.entity.Movies;
import com.example.Assignment.model.entity.Ratings;
import com.example.Assignment.repository.MovieRepository;
import com.example.Assignment.repository.RatingRepository;
import com.example.Assignment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    public static final int MAX_ID_LENGTH = 7;
    public static final String ID_PREFIX = "tt";

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    public Movies getMovieById(String id){
        return movieRepository.findById(id).get();
    }

    public List<Movies> findLast10OrderByRuntimeMinutesDesc(){
        return movieRepository.findFirst10ByOrderByRuntimeMinutesDesc();
    }
    public String saveNewMovie(Movies movies) {
        Movies lastMovie = movieRepository.findTopByOrderByTconstDesc();
        String newId = getNewId(lastMovie.getTconst());
        movies.setTconst(newId);
        Movies movies1 = movieRepository.save(movies);
        return "Success";
    }

    private String getNewId(String lastId){
        int id = Integer.parseInt(lastId.substring(2));
        StringBuilder newId = new StringBuilder(String.valueOf(id + 1));
        int len = newId.length();
        for(int i=1;i<=MAX_ID_LENGTH - len;i++){
            newId.insert(0, "0");
        }
        newId.insert(0,ID_PREFIX);
        System.out.println("New Id - " + newId);
        return newId.toString();
    }

    public List<MovieRatingDto> getTopRatedMovies(){
        List<Ratings> ratingsList = ratingRepository.findByAverageRatingGreaterThan(6.0);

        List<String> ids =ratingsList.stream()
                .map(Ratings::getTconst)
                .collect(Collectors.toList());
        List<Movies> moviesList = movieRepository.findAllById(ids);

        return prepareMovieRatings(ratingsList,moviesList);
    }

    public List<MovieRatingDto> prepareMovieRatings(List<Ratings> ratingsList,List<Movies> moviesList){
        Map<String,Double> ratingsMap = ratingsList.stream()
                .collect(Collectors.toMap(Ratings::getTconst,Ratings::getAverageRating));
        return moviesList.stream().map(movies ->
                MovieRatingDto.builder()
                        .tconst(movies.getTconst())
                        .primaryTitle(movies.getPrimaryTitle())
                        .genres(movies.getGenres())
                        .averageRating(ratingsMap.get(movies.getTconst()))
                        .build()).collect(Collectors.toList());
    }

}
