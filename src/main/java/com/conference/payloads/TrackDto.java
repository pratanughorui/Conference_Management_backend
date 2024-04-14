package com.conference.payloads;

import java.util.List;

import com.conference.entities.Reviewer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TrackDto {
    private int track_id;
    private String track_name;
    private List<TopicDto> topics;
    private List<ReviewerDto> reviewers;
}
