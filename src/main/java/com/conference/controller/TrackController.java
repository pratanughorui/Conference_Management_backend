package com.conference.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entities.Conference;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.TrackDto;
import com.conference.services.ConferenceService;
import com.conference.services.TrackService;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TrackService trackService;

    @PostMapping("/createtrack/{conference_id}")
    public ResponseEntity<String> createTrack(@RequestBody List<String> tracks,
            @PathVariable Integer conference_id) {
        // Uncomment the line below when you're ready to save the tracks
        this.trackService.saveTrack(tracks, conference_id);

        // System.out.println("Tracks: " + tracks);
        // System.out.println("Conference ID: " + conference_id);

        return ResponseEntity.ok("Tracks saved successfully");
    }

    @GetMapping("/getalltracks/{conference_id}")
    public List<TrackDto> getalltracks(@PathVariable Integer conference_id) {
        List<TrackDto> trackDtos = this.trackService.getalltracks(conference_id);
        return trackDtos;
    }

}
