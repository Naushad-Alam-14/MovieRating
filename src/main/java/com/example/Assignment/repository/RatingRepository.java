package com.example.Assignment.repository;

import com.example.Assignment.model.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, String> {
    List<Ratings> findByAverageRatingGreaterThan(Double rating);

}
