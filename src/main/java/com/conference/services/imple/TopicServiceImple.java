package com.conference.services.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conference.entities.Conference;
import com.conference.entities.Topics;
import com.conference.entities.Track;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.repositories.TopicRepo;
import com.conference.repositories.TrackRepo;
import com.conference.services.TopicService;

@Service
public class TopicServiceImple implements TopicService {
    @Autowired
    private TrackRepo trackRepo;
    @Autowired
    private TopicRepo topicRepo;

    @Override
    public void saveTopics(List<String> topics, Integer track_id) {

        Track track = this.trackRepo.findById(track_id)
                .orElseThrow(() -> new ResourceNotFoundException("Track", "id", track_id));

        for (String topic : topics) {
            Topics to = new Topics();
            to.setTopic_name(topic);
            to.setTrack(track);
            this.topicRepo.save(to);
        }

    }

}
