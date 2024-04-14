package com.conference.payloads;

import com.conference.entities.Authors_work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CoAuthorsDto {
    private int coauthor_id;
    private String name;
    private String email;
    private String mobile;
    private String affiliation;
    private String country;
    private String googleScholarId;
    private String orchidId;

}
