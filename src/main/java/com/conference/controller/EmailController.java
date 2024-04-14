package com.conference.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entities.Email;
import com.conference.payloads.emailRequestDto;
import com.conference.services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/Email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{topic_id}")
    public ResponseEntity<?> emailsend(@RequestBody emailRequestDto emailRequestDto, @PathVariable Integer topic_id)
            throws MessagingException, IOException {
        // System.out.println(email.toString());
        // emailService.sendEmail(email.getTo(), email.getSubject(),
        // email.getMessage());
        System.out.println(emailRequestDto.toString());
        emailService.data_fetch(topic_id, emailRequestDto);

        return ResponseEntity.ok("done");
    }
}
