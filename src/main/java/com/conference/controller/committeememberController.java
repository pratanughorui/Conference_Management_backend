package com.conference.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.payloads.UserDto;
import com.conference.services.CommitteemamberService;

@RestController
@RequestMapping("/committeemember")
public class committeememberController {

    @Autowired
    private CommitteemamberService committeemamberService;

    // @PostMapping("/createCommitteeMember")
    // public ResponseEntity<?> CreateCommitteeMember(@RequestBody UserDto userDto)
    // {
    // this.committeemamberService.CreateCommitteeMember(userDto);
    // return new ResponseEntity<>(Map.of("message", "Committee member created
    // Successfully"), HttpStatus.OK);
    // }

}
