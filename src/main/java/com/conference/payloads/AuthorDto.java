package com.conference.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private int author_id;
    @NotEmpty
    private String name;
    private String address;
    @NotEmpty
    private String city;
    private String state;
    private String password;
    @NotEmpty
    private String contactNumber;
    @NotEmpty
    private String email;
    // private ConferenceDto conference;
    // private AuthorWorkDto authorWorkDto;
    // private Set<AuthorWorkDto> author_Works;

    // public void setauthor_Works(AuthorWorkDto author_Work) {
    // this.author_Works.add(author_Work);
    // }

    // public Set<AuthorWorkDto> getauthor_Works() {
    // return author_Works;
    // }
}