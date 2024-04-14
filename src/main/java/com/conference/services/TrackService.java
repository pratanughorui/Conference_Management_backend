package com.conference.services;

import java.util.List;

import com.conference.entities.Conference;
import com.conference.payloads.TrackDto;

public interface TrackService {
    void saveTrack(List<String> trackNames, Integer conference_id);

    List<TrackDto> getalltracks(Integer conference_id);
}
