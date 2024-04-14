package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
