package com.conference.services.imple;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.conference.entities.Authors_work;
import com.conference.entities.Reviewer;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.AllotmentDto;
import com.conference.payloads.AuthorWorkDto;
import com.conference.payloads.ReviewerDto;

import com.conference.repositories.Authors_workRepo;
import com.conference.repositories.ReviewerRepo;
import com.conference.services.AllotmentService;

@Service
public class AllotmentServiceImple implements AllotmentService {
    @Autowired
    private Authors_workRepo authors_workRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewerRepo reviewerRepo;

    @Override
    public void createallotment(List<AllotmentDto> allotmentDtos) {
        for (AllotmentDto dto : allotmentDtos) {

            Reviewer reviewer = this.reviewerRepo.findById(dto.getReviewer2())
                    .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", dto.getReviewer2()));

            Authors_work authors_work = this.authors_workRepo.findById(dto.getAuthors_work())
                    .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", dto.getAuthors_work()));
            if (reviewer.getAuthorWorks().contains(authors_work)) {
                throw new DataIntegrityViolationException("This user is already exist");
            }
            // authors_work.getReviewers().add(reviewer);
            // this.authors_workRepo.save(authors_work);

            reviewer.getAuthorWorks().add(authors_work);
            this.reviewerRepo.save(reviewer);

        }
    }

}
