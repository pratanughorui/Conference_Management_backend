package com.conference.services.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.entities.Committee;
import com.conference.entities.Conference;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.repositories.CommitteeRepo;
import com.conference.repositories.ConferenceRepo;
import com.conference.services.CommitteeService;

@Service
public class CommitteeServiceImple implements CommitteeService {
    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private CommitteeRepo committeeRepo;

    @Override
    public void createcommittee(List<String> committee, Integer conference_id) {
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", conference_id));
        for (String com : committee) {
            Committee c = new Committee();
            c.setCommittee_name(com);
            c.setConference(conference);
            this.committeeRepo.save(c);
        }
    }

}
