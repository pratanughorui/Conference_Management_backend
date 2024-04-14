package com.conference.payloads;

import java.util.List;

import com.conference.entities.Authors_work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicDto {
    private int topic_id;
    private String topic_name;
    private List<AuthorWorkDto> authors;
}
