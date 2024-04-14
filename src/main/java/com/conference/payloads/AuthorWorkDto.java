package com.conference.payloads;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.conference.entities.CoAuthors;
import com.conference.entities.Conference;
import com.conference.entities.Users;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthorWorkDto {
    private int author_id;
    private String name;
    private String address;
    private String state;
    private String country;
    private String cont_no;
    private String email;
    private String title;
    private String track;
    private String key_words;
    private String abstractText;
    private String affiliation;
    // private String city;
    private String pdf_name;
    private List<CoAuthorsDto> CoAuthors;
    // private Conference conferences;

    // private int status;
    // private ConferenceDto conference;
    // private AuthorDto author;

}
