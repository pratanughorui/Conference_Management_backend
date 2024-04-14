package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entities.Committee;

public interface CommitteeRepo extends JpaRepository<Committee, Integer> {

}
