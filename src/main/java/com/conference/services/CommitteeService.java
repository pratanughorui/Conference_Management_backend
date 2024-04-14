package com.conference.services;

import java.util.List;

public interface CommitteeService {
    void createcommittee(List<String> committee, Integer conference_id);

}
