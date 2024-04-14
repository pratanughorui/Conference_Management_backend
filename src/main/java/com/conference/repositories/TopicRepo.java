package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entities.Topics;

public interface TopicRepo extends JpaRepository<Topics, Integer> {

}
