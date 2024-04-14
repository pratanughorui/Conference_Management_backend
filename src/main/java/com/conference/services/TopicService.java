package com.conference.services;

import java.util.List;

public interface TopicService {
    void saveTopics(List<String> topics, Integer track_id);
}
