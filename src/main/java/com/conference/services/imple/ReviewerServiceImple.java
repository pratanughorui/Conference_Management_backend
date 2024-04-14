package com.conference.services.imple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.tools.Trace;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.conference.entities.Conference;
import com.conference.entities.Grading;
import com.conference.entities.Questions;
import com.conference.entities.Review;
import com.conference.entities.Reviewer;
import com.conference.entities.Track;
import com.conference.entities.Users;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.GradingDto;
import com.conference.payloads.QuestionsDto;
import com.conference.payloads.ReviewDto;
import com.conference.payloads.ReviewerDto;
import com.conference.payloads.UserDto;
import com.conference.repositories.ConferenceRepo;
import com.conference.repositories.ReviewRepo;
import com.conference.repositories.ReviewerRepo;
import com.conference.repositories.TrackRepo;

import com.conference.services.ReviewerService;

@Service
public class ReviewerServiceImple implements ReviewerService {

    @Autowired
    private ReviewerRepo reviewerRepo;
    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TrackRepo trackRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public boolean createReviewer(List<ReviewerDto> reviewerDto, Integer track_id) {
        Track track = this.trackRepo.findById(track_id)
                .orElseThrow(() -> new ResourceNotFoundException("Track", "id",
                        track_id));
        for (ReviewerDto reviewer : reviewerDto) {
            String email = reviewer.getEmail();
            Reviewer existreviewer = this.reviewerRepo.findByEmail(email);
            if (existreviewer == null) {
                Reviewer newuser = this.modelMapper.map(reviewer, Reviewer.class);
                newuser.getTracks().add(track);
                this.reviewerRepo.save(newuser);
            } else {
                if (track.getReviewers().contains(existreviewer)) {
                    throw new DataIntegrityViolationException("This user is already exist");
                } else {
                    existreviewer.getTracks().add(track);
                    this.reviewerRepo.save(existreviewer);
                }
            }
        }
        return true;
    }

    @Override
    public List<ReviewerDto> getallreviewers(Integer conference_id) {
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
                        conference_id));
        List<Track> tracks = conference.getTracks();

        List<Reviewer> reviewers = new ArrayList<>();
        for (Track t : tracks) {
            reviewers.addAll(t.getReviewers());
        }
        List<ReviewerDto> reviewerDtos = reviewers.stream()
                .map(con -> this.modelMapper.map(con, ReviewerDto.class))
                .collect(Collectors.toList());
        return reviewerDtos;
    }

    @Override
    public List<ReviewerDto> getallReviewersbeforerecentdate() {
        // TODO Auto-generated method stub
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = currentDate.format(formatter);
        List<Conference> conferences = this.conferenceRepo.findAllConferencesBeforeDate(now);
        List<Track> tracks = new ArrayList<>();
        for (Conference con : conferences) {
            tracks.addAll(con.getTracks());
        }
        List<Reviewer> reviewers = new ArrayList<>();
        for (Track c : tracks) {
            reviewers.addAll(c.getReviewers());
        }
        List<ReviewerDto> reviewerDtos = reviewers.stream().map(con -> this.modelMapper.map(con, ReviewerDto.class))
                .collect(Collectors.toList());
        return reviewerDtos;
    }
    /*
     * @Override
     * public List<UserDto> getAllUserBeforeRecentDate() {
     * LocalDateTime currentDate = LocalDateTime.now();
     * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     * String now = currentDate.format(formatter);
     * List<Conference> conferences =
     * this.conferenceRepo.findAllConferencesBeforeDate(now);
     * List<Users> users = new ArrayList<>();
     * for (Conference c : conferences) {
     * users.addAll(c.getUser());
     * }
     * List<UserDto> users2 = users.stream().map(con -> this.modelMapper.map(con,
     * UserDto.class))
     * .collect(Collectors.toList());
     * return users2;
     * 
     * }
     */

    @Override
    public List<ReviewerDto> getallreviewersbytrack(Integer track_id) {
        // TODO Auto-generated method stub
        Track track = this.trackRepo.findById(track_id)
                .orElseThrow(() -> new ResourceNotFoundException("Track", "id",
                        track_id));
        List<Reviewer> reviewers = track.getReviewers();
        List<ReviewerDto> reviewerDtos = reviewers.stream().map(con -> this.modelMapper.map(con, ReviewerDto.class))
                .collect(Collectors.toList());
        return reviewerDtos;

    }

    @Override
    public ReviewerDto getReviewerById(Integer reviewerId) {
        Reviewer reviewer = this.reviewerRepo.findById(reviewerId)
                .orElseThrow(() -> new ResourceNotFoundException("Track", "id",
                        reviewerId));
        ReviewerDto reviewerDto = this.modelMapper.map(reviewer, ReviewerDto.class);
        return reviewerDto;
    }

    public Review dtotoclass(ReviewDto revieweDto) {
        Review re = new Review();
        re.setAcceptance(revieweDto.getAcceptance());
        re.setPaperId(revieweDto.getPaperId());
        re.setReviewDate(revieweDto.getReviewDate());
        re.setTotalScore(revieweDto.getTotalScore());
        re.setReviewId(revieweDto.getReviewerId());
        return re;
    }

    @Override
    public void review(List<QuestionsDto> questionsDtos, List<GradingDto> gradingDtos, ReviewDto revieweDto) {

        Review review = this.dtotoclass(revieweDto);
        System.out.println(revieweDto.getTotalScore());
        List<Grading> grade = gradingDtos.stream()
                .map(con -> this.modelMapper.map(con, Grading.class))
                .collect(Collectors.toList());
        for (Grading g : grade) {
            g.setReviews(review);
        }
        review.setGrading(grade);
        List<Questions> qus = questionsDtos.stream()
                .map(con -> this.modelMapper.map(con, Questions.class))
                .collect(Collectors.toList());
        for (Questions q : qus) {
            q.setReviews(review);
        }
        review.setQuestionss(qus);
        this.reviewRepo.save(review);
    }

}
