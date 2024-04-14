package com.conference.services;

import java.util.List;
import java.util.Set;

import com.conference.payloads.ConferenceDto;
import com.conference.payloads.UserDto;

public interface ConferenceService {
    ConferenceDto createConference(ConferenceDto conferencedDto);

    // ConferenceDto updateConference(ConferenceDto conference, Integer
    // conference_id);

    // void deleteConference(Integer conference_id);

    void setdatainsession(Integer conference_id);

    Integer getdatafromsession();

    List<ConferenceDto> getallConference();

    ConferenceDto getConferenceById(Integer conference_id);

    ConferenceDto getAllConferenceBtwDate();

    ConferenceDto getconferencefromsession();

    // public Set<UserDto> GetAllUsersByConference(Integer conference_id);

    // Set<Author_Work> getauth(Integer conference_id);

}
