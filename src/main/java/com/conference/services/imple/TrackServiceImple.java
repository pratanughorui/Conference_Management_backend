package com.conference.services.imple;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.entities.Conference;
import com.conference.entities.Track;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.TrackDto;
import com.conference.repositories.ConferenceRepo;
import com.conference.repositories.TrackRepo;
import com.conference.services.TrackService;

@Service
public class TrackServiceImple implements TrackService {
    @Autowired
    private TrackRepo trackRepo;
    @Autowired
    private ConferenceRepo conferenceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveTrack(List<String> trackNames, Integer conference_id) {
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", conference_id));

        for (String trackName : trackNames) {
            Track track = new Track();
            track.setTrack_name(trackName);
            track.setConference(conference);
            trackRepo.save(track);
        }

    }

    @Override
    public List<TrackDto> getalltracks(Integer conference_id) {
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", conference_id));
        List<Track> tracks = conference.getTracks();
        List<TrackDto> trackDtos = tracks.stream().map(con -> this.modelMapper.map(con, TrackDto.class))
                .collect(Collectors.toList());
        return trackDtos;
    }
}
