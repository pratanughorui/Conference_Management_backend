package com.conference.services;

import java.util.List;

import com.conference.payloads.GradingDto;
import com.conference.payloads.QuestionsDto;
import com.conference.payloads.ReviewDto;
import com.conference.payloads.ReviewerDto;
import com.conference.payloads.UserDto;

public interface ReviewerService {
    public boolean createReviewer(List<ReviewerDto> reviewerDto, Integer track_id);

    List<ReviewerDto> getallreviewers(Integer conference_id);

    List<ReviewerDto> getallreviewersbytrack(Integer track_id);

    List<ReviewerDto> getallReviewersbeforerecentdate();

    ReviewerDto getReviewerById(Integer reviewerId);

    void review(List<QuestionsDto> questionsDtos, List<GradingDto> gradingDtos, ReviewDto reviewDto);

}