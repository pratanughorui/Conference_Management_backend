package com.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.services.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/createtopic/{track_id}")
    public ResponseEntity<?> CreateTopic(@RequestBody List<String> topics,
            @PathVariable Integer track_id) {

        this.topicService.saveTopics(topics, track_id);
        return ResponseEntity.ok("Topics saved successfully");
    }
}
