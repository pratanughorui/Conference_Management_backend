package com.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.services.CommitteeService;

@RestController
@RequestMapping("/committee")
public class CommitteeController {
    @Autowired
    private CommitteeService committeeService;

    @PostMapping("/createcommittee/{conference_id}")
    public ResponseEntity<?> createcommittee(@RequestBody List<String> committee, @PathVariable Integer conference_id) {
        this.committeeService.createcommittee(committee, conference_id);
        return ResponseEntity.ok("committee created successfully");
    }
}
