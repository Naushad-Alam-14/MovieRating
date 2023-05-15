package com.example.Assignment.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRatingDto {

    private String tconst;
    private String primaryTitle;
    private String genres;
    private double averageRating;
}
