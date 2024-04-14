package com.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conference.payloads.GradingDto;
import com.conference.payloads.QuestionsDto;
import com.conference.payloads.ReviewDto;
import com.conference.payloads.ReviewerDto;
import com.conference.payloads.UserDto;
import com.conference.services.ReviewerService;
import com.conference.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/Reviewer")
public class ReviewersController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReviewerService reviewerService;

    @PostMapping("/createreviewer/{track_id}")
    public ResponseEntity<?> reviewerCreation(@RequestBody List<ReviewerDto> reviewerDto,
            @PathVariable Integer track_id) {
        this.reviewerService.createReviewer(reviewerDto, track_id);
        return new ResponseEntity<>("Reviewers created successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/getallreviwers/{conference_id}")
    public List<ReviewerDto> getallreviewers(@PathVariable Integer conference_id) {
        List<ReviewerDto> reviewer = this.reviewerService.getallreviewers(conference_id);
        return reviewer;
    }

    @GetMapping("/getallreviwersbytrack/{track_id}")
    public List<ReviewerDto> getallreviewersbytrack(@PathVariable Integer track_id) {
        List<ReviewerDto> reviewer = this.reviewerService.getallreviewersbytrack(track_id);
        return reviewer;
    }

    @GetMapping("/getallreviewersbeforerecentdate")
    public List<ReviewerDto> getallreviewersbeforerecentdate() {
        List<ReviewerDto> reviewer = this.reviewerService.getallReviewersbeforerecentdate();
        return reviewer;
    }

    @GetMapping("/getreviewerbyid/{reviewerId}")
    public ReviewerDto getreviewerbyid(@PathVariable Integer reviewerId) {
        ReviewerDto reviewer = this.reviewerService.getReviewerById(reviewerId);
        return reviewer;
    }

    @PostMapping("/review")
    public ResponseEntity<?> getAuthorsworkbyid(@RequestParam("name") String authorWorkDtoJson,
            @RequestParam("coauthor") String co, @RequestParam("review") String review)
            throws JsonMappingException, JsonProcessingException {
        // System.out.println(grade);
        // for (GradingDto gradingDto : grade) {
        // gradingDto.toString();
        // }
        // System.out.println(authorWorkDtoJson);
        // System.out.println(co);
        ObjectMapper objectMapper = new ObjectMapper();

        List<GradingDto> gradingDtos = objectMapper.readValue(authorWorkDtoJson,
                new TypeReference<List<GradingDto>>() {
                });

        List<QuestionsDto> questionsDtos = objectMapper.readValue(co,
                new TypeReference<List<QuestionsDto>>() {
                });
        // for (QuestionsDto questionsDto : questionsDtos) {
        // System.out.println(questionsDto.toString());
        // }
        ReviewDto reviewDto = objectMapper.readValue(review,
                ReviewDto.class);
        System.out.println(reviewDto.toString());
        // GradingDto authorWorkDto = objectMapper.readValue(authorWorkDtoJson,
        // GradingDto.class);
        this.reviewerService.review(questionsDtos, gradingDtos, reviewDto);
        return new ResponseEntity<>("done", HttpStatus.OK);

    }

}
