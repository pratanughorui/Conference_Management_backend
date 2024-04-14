package com.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.payloads.AllotmentDto;
import com.conference.services.AllotmentService;

@RestController
@RequestMapping("/allotment")
public class AllotmentsController {
    @Autowired
    private AllotmentService allotmentService;

    @PostMapping("/papersallot")
    public ResponseEntity<?> createallotment(@RequestBody List<AllotmentDto> allotment) {
        this.allotmentService.createallotment(allotment);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }
}
