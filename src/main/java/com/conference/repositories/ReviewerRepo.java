package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.conference.entities.Reviewer;
import com.conference.entities.Users;

public interface ReviewerRepo extends JpaRepository<Reviewer, Integer> {
    @Query("SELECT c FROM Reviewer c WHERE c.email = :email")
    Reviewer findByEmail(@Param("email") String email);
}
