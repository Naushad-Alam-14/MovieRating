package com.example.Assignment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    @Id
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String genres;
    private int runtimeMinutes;
}