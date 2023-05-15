package com.example.Assignment.model;


import com.example.Assignment.model.entity.Movies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String tconst;
    private String primaryTitle;
    private String genres;
    private int runtimeMinutes;

    public MovieDto(Movies movies) {
        this.tconst = movies.getTconst();
        this.primaryTitle = movies.getPrimaryTitle();
        this.genres = movies.getGenres();
        this.runtimeMinutes = movies.getRuntimeMinutes();
    }
}
