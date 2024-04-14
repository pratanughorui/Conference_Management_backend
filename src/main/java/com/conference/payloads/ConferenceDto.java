package com.conference.payloads;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.conference.entities.Track;
import com.conference.entities.Users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConferenceDto {
    private int conference_id;

    @NotEmpty
    private String conferences_title;
    private String website;
    private String venue;
    private String address;
    private String place;
    private String state;
    private String country;
    private String fromDate;
    private String toDate;
    private String datecallpaper;
    private String lastdatesubpaper;
    private String dateofallotpaper;
    private String lastdaterevsub;

    private String creationDateTimeAsString;
    private List<TrackDto> tracks;
    private List<CommitteeDto> committees;

    // private Set<AuthorWorkDto> author_Works;
    // private Set<UserDto> user;

    // public void setauthor_Works(AuthorWorkDto author_Work) {
    // this.author_Works.add(author_Work);
    // }

    // public void setuser(UserDto user) {
    // this.user.add(user);
    // }

    // public Set<AuthorWorkDto> getauthor_Works() {
    // return author_Works;
    // }

    // public Set<UserDto> getuser() {
    // return user;
    // }
}
