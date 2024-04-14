package com.conference.services;

import java.util.List;

import com.conference.payloads.AllotmentDto;

public interface AllotmentService {
    void createallotment(List<AllotmentDto> allotmentDtos);
}
