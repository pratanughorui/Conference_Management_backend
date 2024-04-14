package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.conference.entities.Track;

public interface TrackRepo extends JpaRepository<Track, Integer> {

}
